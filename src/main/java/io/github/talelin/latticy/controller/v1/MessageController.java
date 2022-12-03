package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.dto.message.CreateOrUpdateMessageDTO;
import io.github.talelin.latticy.model.BookDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.model.MessageDO;
import io.github.talelin.latticy.service.MessageService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 信息模板控制器
 */
@RestController
@RequestMapping("/v1/message")
@Validated
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public MessageDO getMessage(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        MessageDO message = messageService.getById(id);
        if (message == null) {
            throw new NotFoundException(10303);
        }
        return message;
    }

    /**
     * 查询所有账号信息
     * @return
     */
    @GetMapping("")
    public List<MessageDO> getMessages() {
        return messageService.findAll();
    }

    @PostMapping("")
    public CreatedVO createMessage(@RequestBody @Validated CreateOrUpdateMessageDTO validator) {
        messageService.createMessage(validator);
        return new CreatedVO(23);
    }


    @PutMapping("/{id}")
    public UpdatedVO updateMessage(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateMessageDTO validator) {
        MessageDO message = messageService.getById(id);
        if (message == null) {
            throw new NotFoundException(10304);
        }
        messageService.updateMessage(message, validator);
        return new UpdatedVO(24);
    }

    /**
     * 删除信息模板
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除信息模板", module = "信息模板")
    public DeletedVO deleteMessage(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        MessageDO message = messageService.getById(id);
        if (message == null) {
            throw new NotFoundException(10303);
        }
        messageService.deleteById(message.getId());
        return new DeletedVO(25);
    }
}
