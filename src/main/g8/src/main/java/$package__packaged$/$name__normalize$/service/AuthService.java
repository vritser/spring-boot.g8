package $package$.$name;format="normalize"$.service;

import $package$.$name;format="normalize"$.core.model.ErrorCode;
import $package$.$name;format="normalize"$.vo.UserLoginForm;
import $package$.$name;format="normalize"$.vo.UserLoginResp;
import $package$.$name;format="normalize"$.vo.PasswordUpdateForm;
import $package$.$name;format="normalize"$.vo.WechatLoginForm;
import $package$.$name;format="normalize"$.vo.WechatLoginResp;

import io.vavr.control.Either;

/**
 * AuthService
 */
public interface AuthService {

    Either<ErrorCode, Integer> modifyPassword(Long userId, PasswordUpdateForm form);

    Either<ErrorCode, UserLoginResp> login(UserLoginForm form);

    Either<ErrorCode, WechatLoginResp> login(WechatLoginForm form);

}
