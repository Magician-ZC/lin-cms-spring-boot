package io.github.talelin.latticy.dto.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 设备创建/更新数据传输对象
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateAccountDTO {

    @NotEmpty(message = "{必须传入账号}")
    @Length(max = 50, message = "{账号长度不能超过1000}")
    private String username;

    @NotEmpty(message = "{必须传入密码}")
    @Length(max = 50, message = "{密码长度不能超过1000}")
    private String password;

}
