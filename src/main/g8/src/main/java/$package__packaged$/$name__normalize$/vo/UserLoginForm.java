package $package$.$name;format="normalize"$.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * UserLoginForm
 */
@Data
@ApiModel("后台登录参数")
public class UserLoginForm {
    private String mobile;
    private String password;
}
