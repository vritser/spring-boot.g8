package $package$.$name;format="normalize"$.utils;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * BaseQueryParams
 */
@Data
@Schema(title = "分页参数")
public class BaseQueryParams {
    /**
     * 页数 起始 0
     */
    @Schema("页数, 起始 1")
    private Integer start = 1;
    
    /**
     * 每页条数
     */
    @Schema("每页条数, 默认 10")
    private Integer limit = 10;

    /**
     * 排序
     */
    @Schema("排序 格式: field:asc")
    private List<String> sort = List.of();

    public <T> Page<T> toPage() {
        return new Page<>(start, limit);
    }

}
