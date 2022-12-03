package io.github.talelin.latticy.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 信息模板创建/更新数据传输对象
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateMessageDTO {

    @NotEmpty(message = "{必须传入文本内容}")
    @Length(max = 1000, message = "{文本内容长度不能超过1000}")
    private String content;

    private String imgUrl;

}
