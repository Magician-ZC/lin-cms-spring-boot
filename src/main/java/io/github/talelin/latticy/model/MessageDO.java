package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 信息内容
 */
@Data
@TableName("message")
public class MessageDO extends BaseModel implements Serializable {

    private static final long serialVersionUID= 4156746107591701183L;

    /**
     * 文本内容
     */
    private String content;

    /**
     * 图片链接
     */
    private String imgUrl;

}
