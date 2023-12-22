package $package$.$name;format="normalize"$.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * PasswordUpdateForm
 */
@Data
@Schema(title = "后台修改密码参数")
public class PasswordUpdateForm {
    private String oldPassword;
    private String newPassword;
}
