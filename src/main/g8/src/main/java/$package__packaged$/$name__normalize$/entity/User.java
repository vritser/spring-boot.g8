package $package$.$name;format="normalize"$.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * User
 */
@Data
@TableName("users")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String name;
    private String mobile;
    private String password;
    private int activation;
    private Boolean freeze;
    private Boolean needRelogin;
    private Boolean isSuper;
}
