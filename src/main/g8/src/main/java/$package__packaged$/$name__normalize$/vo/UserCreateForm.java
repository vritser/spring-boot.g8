package $package$.$name;format="normalize"$.vo;

import $package$.$name;format="normalize"$.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * UserCreateForm
 */
@Data
@ApiModel("创建用户参数")
public class UserCreateForm {
    private String name;
    private String mobile;
    private String password;
    private Boolean isSuper;
    private Boolean freeze;

    public User asUser(String password) {
        return new User()
            .setName(name)
            .setMobile(mobile)
            .setPassword(password)
            .setIsSuper(isSuper)
            .setFreeze(freeze)
            .setActivation(1)
            .setNeedRelogin(false);
    }

}
