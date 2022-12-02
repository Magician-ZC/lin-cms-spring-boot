package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wrs 393895279@qq.com
 * @date 2020/5/8.
 * @desc
 */
@Data
public class CommandDO implements Serializable {

    private static final long serialVersionUID= 6128988308896525608L;

    /**
     * 设备id
     */
    private String deviceId;
    private Integer action;

    /**
     * 账号名称
     */
    private String username;
    /**
     * 账号密码
     */
    private String password;

    /**
     * 聊天发送对象
     */
    private String sendUsername;
    /**
     * 聊天内容
     */
    private String msg;
    /**
     * 聊天图片地址
     */
    private String imgUrl;

}
