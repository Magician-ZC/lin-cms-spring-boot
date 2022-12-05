package io.github.talelin.latticy.model;

import lombok.Data;

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
     * 账号名称
     */
    private String account;
    /**
     * 账号密码
     */
    private String password;

    /**
     * 聊天发送对象
     */
    private String sendName;
    /**
     * 聊天内容
     */
    private String msgContent;
    /**
     * 聊天图片地址
     */
    private String msgUrl;

    @Override
    public String toString() {
        return "CommandDO{" +
                "username='" + account + '\'' +
                ", password='" + password + '\'' +
                ", sendUsername='" + sendName + '\'' +
                ", msg='" + msgContent + '\'' +
                ", imgUrl='" + msgUrl + '\'' +
                '}';
    }
}
