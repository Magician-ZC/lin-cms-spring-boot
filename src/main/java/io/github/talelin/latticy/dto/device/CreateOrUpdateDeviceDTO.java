package io.github.talelin.latticy.dto.device;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotEmpty;

/**
 * 设备创建/更新数据传输对象
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateDeviceDTO {

    @NotEmpty(message = "{必须传入设备id}")
    @Length(max = 50, message = "{设备id长度不能超过50}")
    private String deviceId;

    private String deviceTag;

    private Integer online;

    private Integer taskStatus;

    @NotEmpty(message = "{必须传入设备版本号}")
    private Integer version;
}
