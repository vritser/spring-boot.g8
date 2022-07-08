package $package$.$name;format="normalize"$.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseEntity {
    @TableId
    private Long id;
    
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
