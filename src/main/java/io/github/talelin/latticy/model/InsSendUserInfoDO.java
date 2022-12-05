package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wrs 393895279@qq.com
 * @date 2020/5/9.
 * @desc ins发送对象
 */
@Data
@TableName("ins_send_user_info")
public class InsSendUserInfoDO extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1736445224925724513L;

    /**
     * 发送状态 0未发送，1发送中，2发送成功
     */
    private Integer status;

    /**
     * 发送对象ins id
     */
    private String username;
}
