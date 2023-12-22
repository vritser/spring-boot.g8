package $package$.$name;format="normalize"$.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import $package$.$name;format="normalize"$.core.annotation.Authorized;
import $package$.$name;format="normalize"$.core.annotation.PassAuth;
import $package$.$name;format="normalize"$.dto.AuthorizedUser;
import $package$.$name;format="normalize"$.core.model.Resp;
import $package$.$name;format="normalize"$.service.AuthService;
import $package$.$name;format="normalize"$.vo.WechatLoginForm;
import $package$.$name;format="normalize"$.vo.UserLoginForm;
import $package$.$name;format="normalize"$.vo.PasswordUpdateForm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * AuthController
 */
@RestController
@RequestMapping("/login")
@Tag(description = "登录接口")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PassAuth
    @PostMapping("/client")
    @Operation(summary = "小程序登录")
    public Resp<?> login(@Validated @RequestBody WechatLoginForm form) {
        return Resp.from(authService.login(form));
    }

    @PassAuth
    @PostMapping
    @ApiOperation("后台用户登录")
    public Resp<?> login(@RequestBody UserLoginForm form) {
        return Resp.from(authService.login(form));
    }

    @PutMapping("/modify")
    @Operation(summary = "后台用户修改密码")
    public Resp<?> modifyPassword(@Authorized AuthorizedUser user, @RequestBody PasswordUpdateForm form) {
        return Resp.from(authService.modifyPassword(user.getId(), form));
    }

}
