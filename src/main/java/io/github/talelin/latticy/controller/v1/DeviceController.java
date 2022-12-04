package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.dto.device.CreateOrUpdateDeviceDTO;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.module.message.WsHandler;
import io.github.talelin.latticy.service.DeviceService;
import io.github.talelin.latticy.service.InsAccountInfoService;
import io.github.talelin.latticy.service.InsSendUserInfoService;
import io.github.talelin.latticy.service.MessageService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.OperateVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 设备控制器
 */
@RestController
@RequestMapping("/v1/device")
@Validated
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private InsAccountInfoService insAccountInfoService;

    @Autowired
    private InsSendUserInfoService insSendUserInfoService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private WsHandler wsHandler;

    @GetMapping("/{id}")
    public DeviceDO getDevice(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        DeviceDO device = deviceService.getById(id);
        if (device == null) {
            throw new NotFoundException(10300);
        }
        return device;
    }

    @GetMapping("")
    public List<DeviceDO> getDevices() {
        return deviceService.findAll();
    }

    @GetMapping("/search/{online}")
    public List<DeviceDO> searchDevice(@PathVariable(value = "online") @PositiveOrZero(message = "在线状态不能为空") Integer online) {
        return deviceService.getAllDeviceByOnline(online);
    }

    @PostMapping("")
    public CreatedVO createDevice(@RequestBody @Validated CreateOrUpdateDeviceDTO validator) {
        DeviceDO device = deviceService.getByDeviceId(validator.getDeviceId());
        if (device == null) {
            deviceService.createDevice(validator);
        } else {
            throw new NotFoundException(10301);
        }
        return new CreatedVO(16);
    }

    @PutMapping("/{id}")
    public UpdatedVO updateDevice(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateDeviceDTO validator) {
        DeviceDO device = deviceService.getById(id);
        if (device == null) {
            throw new NotFoundException(10300);
        }
        deviceService.updateDevice(device, validator);
        return new UpdatedVO(17);
    }


    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除设备", module = "设备")
    public DeletedVO deleteDevice(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        DeviceDO device = deviceService.getById(id);
        if (device == null) {
            throw new NotFoundException(10300);
        }
        deviceService.deleteById(device.getId());
        return new DeletedVO(18);
    }

    @PutMapping("/task/{id}")
    public OperateVO startTask(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        //获取当前设备信息
        DeviceDO device = deviceService.getById(id);
        //获取账号信息
        List<InsAccountInfoDO> insAccountInfoList = insAccountInfoService.findAll();
        InsAccountInfoDO insAccountInfo = null;
        //获取接受者账号
        List<InsSendUserInfoDO> receiverList = insSendUserInfoService.findAll();
        InsSendUserInfoDO receiver = null;
        //获取发送内容
        MessageDO message = messageService.getById(1);

        for (int i = 0; i < insAccountInfoList.size(); i++) {
            insAccountInfo = insAccountInfoList.get(i);
            //账号信息正常进入下一步,否则继续下一行
            if (insAccountInfo.getStatus() == 0) {
                //如果账号信息上一次被发送给该设备或者该账号信息没有被使用过,继续发送给该设备
                if (insAccountInfo.getLastDeviceId() == null || device.getDeviceId() == insAccountInfo.getLastDeviceId()) {
                    //继续给这台设备发送指令
                    receiver = getReceive(receiverList);
                    break;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        CommandDO command = new CommandDO();
        if(insAccountInfo != null && receiver != null && message != null){
            command.setDeviceId(device.getDeviceId());
            command.setUsername(insAccountInfo.getUsername());
            command.setPassword(insAccountInfo.getPassword());
            command.setSendUsername(receiver.getUsername());
            command.setMsg(message.getContent());
        }

        try {
            wsHandler.sendMessage(1,command.toString());
        } catch (IOException e) {
            return new OperateVO(31);
        }
        return new OperateVO(30);
    }

    private InsSendUserInfoDO getReceive(List<InsSendUserInfoDO> receiverList){
        for (InsSendUserInfoDO receiver:receiverList) {
            if(receiver.getStatus() == 0){
                return receiver;
            }
        }
        return null;
    }
}
