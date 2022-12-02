package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * ins账号列表
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ins_account_info")
@EqualsAndHashCode(callSuper = true)
public class InsAccountInfoDO extends BaseModel implements Serializable {

    private static final long serialVersionUID= -1388321833414589409L;


    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String lastDeviceId;

    /**
     * ins账号：0正常 1密码错误 2 账号冻结 3 当天任务已完成
     */
    private Integer status;

    /**
     * 0空闲 1运行中
     */
    private Integer taskStatus;

}
