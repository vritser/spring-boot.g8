package $package$.$name;format="normalize"$.utils;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BaseQueryParams
 */
@Data
@ApiModel("分页参数")
public class BaseQueryParams {
    /**
     * 页数 起始 0
     */
    @ApiModelProperty("页数, 起始 1")
    private Integer start = 1;
    
    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数, 默认 10")
    private Integer limit = 10;

    /**
     * 排序
     */
    @ApiModelProperty("排序 格式: field:asc")
    private List<String> sort = List.of();

    public <T> Page<T> toPage() {
        return new Page<>(start, limit);
    }

}
