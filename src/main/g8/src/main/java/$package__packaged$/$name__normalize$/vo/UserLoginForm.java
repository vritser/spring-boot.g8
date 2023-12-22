package $package$.$name;format="normalize"$.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * UserLoginForm
 */
@Data
@Schema(title = "后台登录参数")
public class UserLoginForm {
    private String mobile;
    private String password;
}
