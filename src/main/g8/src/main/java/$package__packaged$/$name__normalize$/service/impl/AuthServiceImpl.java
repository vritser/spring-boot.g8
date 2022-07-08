package $package$.$name;format="normalize"$.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import $package$.$name;format="normalize"$.core.model.ErrorCode;
import $package$.$name;format="normalize"$.dao.ClientUserDao;
import $package$.$name;format="normalize"$.dao.UserDao;
import $package$.$name;format="normalize"$.entity.ClientUser;
import $package$.$name;format="normalize"$.service.AuthService;
import $package$.$name;format="normalize"$.utils.JwtUtil;
import $package$.$name;format="normalize"$.vo.UserLoginForm;
import $package$.$name;format="normalize"$.vo.UserLoginResp;
import $package$.$name;format="normalize"$.vo.PasswordUpdateForm;
import $package$.$name;format="normalize"$.vo.WechatLoginForm;
import $package$.$name;format="normalize"$.vo.WechatLoginResp;

import io.vavr.control.Either;
import io.vavr.control.Option;

/**
 * AuthServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ClientUserDao clientUserDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public Either<ErrorCode, Integer> modifyPassword(Long userId, PasswordUpdateForm form) {
        var password = passwordEncoder.encode(form.getNewPassword());

        return Option.of(userDao.selectById(userId))
            .toEither(ErrorCode.UserNotFound)
            .filterOrElse(user -> passwordEncoder.matches(form.getOldPassword(), user.getPassword()),
                          __   -> ErrorCode.InvalidPassword)
            .map(user -> user.setPassword(password))
            .map(userDao::updateById);
    }

    public Either<ErrorCode, UserLoginResp> login(UserLoginForm form) {
        return Option.ofOptional(userDao.findByMobile(form.getMobile()))
            .toEither(ErrorCode.UserNotFound)
            .filterOrElse(user -> !user.getFreeze(),
                          __   -> ErrorCode.UserFreezed)
            .filterOrElse(user -> passwordEncoder.matches(form.getPassword(), user.getPassword()),
                          __   -> ErrorCode.InvalidPassword)
            .map(user -> {
                    String token = jwtUtil.authorize(user.getId(), 2);
                    return new UserLoginResp()
                        .setId(user.getId())
                        .setName(user.getName())
                        .setMobile(user.getMobile())
                        .setIsSuper(user.getIsSuper())
                        .setToken(token);
                });
    }

    /**
     * 通过微信 openid 登录
     */
    public Either<ErrorCode, WechatLoginResp> login(WechatLoginForm form) {
        var lockKey = "lock_login:" + form.getOpenid();
        var locked = redisTemplate.opsForValue().setIfAbsent(lockKey, "1", 5, TimeUnit.SECONDS);
        if (!locked) return Either.left(ErrorCode.DoNotRepeat);

        return Option.ofOptional(clientUserDao.findByOpenid(form.getOpenid()))
            .orElse(() -> {
                    var user = form.asEntity();
                    clientUserDao.insert(user);
                    return Option.some(user);
                })
            .map(user -> {
                    String token = jwtUtil.authorize(user.getId(), 1);
                    return new WechatLoginResp()
                        .setId(user.getId())
                        .setNickname(user.getNickname())
                        .setMobile(user.getMobile())
                        .setToken(token);
                })
            .toEither(ErrorCode.BadRequest);
    }

}
