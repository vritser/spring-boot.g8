package $package$.$name;format="normalize"$.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserLoginResp
 */
@Data
@Accessors(chain = true)
public class UserLoginResp {
    private Long id;
    private String name;
    private String mobile;
    private String token;
    private Boolean isSuper;
}
