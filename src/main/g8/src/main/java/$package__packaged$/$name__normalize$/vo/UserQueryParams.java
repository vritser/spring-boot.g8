package $package$.$name;format="normalize"$.vo;

import $package$.$name;format="normalize"$.utils.BaseQueryParams;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UserQueryParams
 */
@Data
@ApiModel("用户列表筛选参数")
@EqualsAndHashCode(callSuper = true)
public class UserQueryParams extends BaseQueryParams {
    private String name;
    private String mobile;
}
