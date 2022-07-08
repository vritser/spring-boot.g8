package $package$.$name;format="normalize"$.core.model;

import lombok.Getter;

/**
 * ErrorCode
 */
@Getter
public enum ErrorCode {

    Success(0, "success"),
    BadRequest(400, "该请求不符合规范"),
    Unauthorized(401, "用户还未登录,请先登录"),
    PermissionDenied(403, "权限不足,请联系管理员"),
    InvalidToken(1000, "无效的Token"),
    DoNotRepeat(1001, "请勿重复操作"),
    UserNotFound(1100, "未找到该用户"), 
    InvalidPassword(1101, "密码错误"), 
    UserFreezed(1102, "用户已冻结");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
