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
 * @author wrs 393895279@qq.com
 * @date 2020/5/9.
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("device")
@EqualsAndHashCode(callSuper = true)
public class DeviceDO extends BaseModel implements Serializable {
    private static final long serialVersionUID = -8932753679410167288L;

    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 设备标识
     */
    private String deviceTag;
    /**
     * 是否在线0 离线，1 在线
     */
    private Integer online;

    /**
     * 0 空闲，1 任务执行中
     */
    private Integer taskStatus;
    private Integer version;
}
