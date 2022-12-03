package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.model.DeviceDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.service.InsAccountInfoService;
import io.github.talelin.latticy.vo.DeletedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * ins账号控制器
 */
@RestController
@RequestMapping("/v1/account")
@Validated
public class InsAccountController {

    @Autowired
    private InsAccountInfoService insAccountInfoService;

    /**
     * 查询所有账号信息
     * @return
     */
    @GetMapping("")
    public List<InsAccountInfoDO> getInsAccounts() {
        return insAccountInfoService.findAll();
    }

    /**
     * 删除账号
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除账号", module = "账号")
    public DeletedVO deleteAccount(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        InsAccountInfoDO accountInfo = insAccountInfoService.getById(id);
        if (accountInfo == null) {
            throw new NotFoundException(10300);
        }
        insAccountInfoService.deleteById(accountInfo.getId());
        return new DeletedVO(22);
    }
}
