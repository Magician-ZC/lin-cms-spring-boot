package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.dto.account.CreateOrUpdateAccountDTO;
import io.github.talelin.latticy.dto.receive.CreateOrUpdateReceiveDTO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.model.InsSendUserInfoDO;
import io.github.talelin.latticy.service.InsSendUserInfoService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 发送对象控制器
 */
@RestController
@RequestMapping("/v1/receive")
@Validated
public class InsSendUserInfoController {

    @Autowired
    private InsSendUserInfoService insSendUserInfoService;


    @GetMapping("/{id}")
    public InsSendUserInfoDO getReceive(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        InsSendUserInfoDO receive = insSendUserInfoService.getById(id);
        if (receive == null) {
            throw new NotFoundException(10302);
        }
        return receive;
    }

    /**
     * 查询所有账号信息
     * @return
     */
    @GetMapping("")
    public List<InsSendUserInfoDO> getReceives() {
        return insSendUserInfoService.findAll();
    }

    @PostMapping("")
    public CreatedVO createReceive(@RequestBody @Validated CreateOrUpdateReceiveDTO validator) {
        if(insSendUserInfoService.isExistReceive(validator.getUsername())){
            throw new NotFoundException(10304);
        }else{
            insSendUserInfoService.createInsAccount(validator);
        }
        return new CreatedVO(20);
    }


    @PutMapping("/{id}")
    public UpdatedVO updateReceive(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateReceiveDTO validator) {
        InsSendUserInfoDO receive = insSendUserInfoService.getById(id);
        if (receive == null) {
            throw new NotFoundException(10302);
        }
        insSendUserInfoService.updateInsAccount(receive, validator);
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
    public DeletedVO deleteReceive(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        InsSendUserInfoDO receive = insSendUserInfoService.getById(id);
        if (receive == null) {
            throw new NotFoundException(10302);
        }
        insSendUserInfoService.deleteById(receive.getId());
        return new DeletedVO(22);
    }
}
