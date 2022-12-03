package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.dto.account.CreateOrUpdateAccountDTO;
import io.github.talelin.latticy.dto.message.CreateOrUpdateMessageDTO;
import io.github.talelin.latticy.model.DeviceDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.model.MessageDO;
import io.github.talelin.latticy.service.InsAccountInfoService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.UpdatedVO;
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

    @GetMapping("/{id}")
    public InsAccountInfoDO getInsAccount(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        InsAccountInfoDO insAccount = insAccountInfoService.getById(id);
        if (insAccount == null) {
            throw new NotFoundException(10302);
        }
        return insAccount;
    }

    /**
     * 查询所有账号信息
     * @return
     */
    @GetMapping("")
    public List<InsAccountInfoDO> getInsAccounts() {
        return insAccountInfoService.findAll();
    }

    @PostMapping("")
    public CreatedVO createInsAccount(@RequestBody @Validated CreateOrUpdateAccountDTO validator) {
        insAccountInfoService.createInsAccount(validator);
        return new CreatedVO(20);
    }


    @PutMapping("/{id}")
    public UpdatedVO updateInsAccount(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateAccountDTO validator) {
        InsAccountInfoDO insAccount = insAccountInfoService.getById(id);
        if (insAccount == null) {
            throw new NotFoundException(10302);
        }
        insAccountInfoService.updateInsAccount(insAccount, validator);
        return new UpdatedVO(21);
    }

    /**
     * 删除账号
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除账号", module = "账号")
    public DeletedVO deleteInsAccount(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        InsAccountInfoDO accountInfo = insAccountInfoService.getById(id);
        if (accountInfo == null) {
            throw new NotFoundException(10302);
        }
        insAccountInfoService.deleteById(accountInfo.getId());
        return new DeletedVO(22);
    }
}
