package $package$.$name;format="normalize"$.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * WechatLoginResp
 */
@Data
@Accessors(chain = true)
public class WechatLoginResp {
    private Long id;
    private String nickname;
    private String mobile;
    private String token;
}
