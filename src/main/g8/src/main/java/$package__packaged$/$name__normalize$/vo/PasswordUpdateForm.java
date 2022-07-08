package $package$.$name;format="normalize"$.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * PasswordUpdateForm
 */
@Data
@ApiModel("后台修改密码参数")
public class PasswordUpdateForm {
    private String oldPassword;
    private String newPassword;
}
