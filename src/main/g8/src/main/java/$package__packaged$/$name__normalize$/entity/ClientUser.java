package $package$.$name;format="normalize"$.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ClientUser
 */
@Data
@Accessors(chain = true)
@TableName("client_users")
@EqualsAndHashCode(callSuper = true)
public class ClientUser extends BaseEntity {
    private String nickname;
    private String mobile;
    private String avatar;
    private Integer gender;
    private LocalDate birth;

    private String openid;
    private String unionid;
}