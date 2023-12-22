package $package$.$name;format="normalize"$.vo;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * UserUpdateForm
 */
@Data
@Schema(title = "更新用户信息参数")
public class UserUpdateForm {
    private Optional<String> name = Optional.empty();
    private Optional<String> mobile = Optional.empty();
    private Optional<String> password = Optional.empty();
    private Optional<Integer> activation = Optional.empty();
    private Optional<Boolean> freeze = Optional.empty();
    private Optional<Boolean> needRelogin = Optional.empty();
    private Optional<Boolean> isSuper = Optional.empty();

    // 角色 id
    private Optional<List<Long>> roles = Optional.empty();
}
