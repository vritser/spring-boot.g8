package $package$.$name;format="normalize"$.vo;

import $package$.$name;format="normalize"$.entity.ClientUser;
import org.hibernate.validator.constraints.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * WechatLoginRequest
 */
@Data
@Schema("小程序登录参数")
public class WechatLoginForm {
    @Length(min = 28, max = 28, message = "openid 格式不正确")
    private String openid;
    
    @Length(max = 64, message = "昵称太长了")
    private String nickname;
    
    private String avatar;
    
    private Integer gender;

    public ClientUser asEntity() {
        return new ClientUser()
            .setNickname(nickname)
            .setOpenid(openid)
            .setAvatar(avatar)
            .setGender(gender);
    }
}
