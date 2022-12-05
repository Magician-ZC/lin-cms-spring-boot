package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.extension.api.R;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.ConvertGson;
import io.github.talelin.latticy.dto.device.CreateOrUpdateDeviceDTO;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.module.message.Response;
import io.github.talelin.latticy.module.message.WsHandler;
import io.github.talelin.latticy.service.DeviceService;
import io.github.talelin.latticy.service.InsAccountInfoService;
import io.github.talelin.latticy.service.InsSendUserInfoService;
import io.github.talelin.latticy.service.MessageService;
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

    @PutMapping("/{id}")
    public UpdatedVO updateDevice(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        DeviceDO device = deviceService.getById(id);
        if (device == null) {
            throw new NotFoundException(10300);
        }
        deviceService.updateDevice(device);
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

    @PutMapping("/task/start/{id}")
    public OperateVO startTask(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        //获取当前设备信息
        DeviceDO device = deviceService.getById(id);
        //获取账号信息
        List<InsAccountInfoDO> insAccountInfoList = insAccountInfoService.selectAllTaskByDeviceId(device.getDeviceId());
        if (insAccountInfoList == null || insAccountInfoList.size() == 0) {
            insAccountInfoList = insAccountInfoService.selectAllTask();
            if (insAccountInfoList == null || insAccountInfoList.size() == 0) {
                return new OperateVO(10401);
            }
        }
        InsAccountInfoDO insAccountInfo = null;
        //获取接受者账号
        List<InsSendUserInfoDO> receiverList = insSendUserInfoService.findAll();
        InsSendUserInfoDO receiver = null;
        //获取发送内容
        List<MessageDO> messageList = messageService.findAll();

        MessageDO message = null;
        if (messageList == null || messageList.size() == 0) {
            return new OperateVO(10402);
        }
        message = messageList.get(0);
        for (int i = 0; i < insAccountInfoList.size(); i++) {
            insAccountInfo = insAccountInfoList.get(i);
            //继续给这台设备发送指令
            receiver = getReceive(receiverList);
            break;
        }
        if (receiver == null) {
            return new OperateVO(10403);
        }
        CommandDO command = new CommandDO();
        command.setAccount(insAccountInfo.getUsername());
        command.setPassword(insAccountInfo.getPassword());
        command.setSendName(receiver.getUsername());
        command.setMsgContent(message.getContent());
        command.setMsgUrl(message.getImgUrl());

        Response response = new Response();
        response.setAction("auto_send");
        response.setType("1");
        response.setResp(ConvertGson.toJson(command));
        for (WebSocketSession session : wsHandler.getSessions()) {
            if (session.getUri().getQuery().equals(device.getDeviceId())) {
                try {
                    //接收者账号更新状态
                    receiver.setStatus(1);
                    insSendUserInfoService.updateInsAccountStatus(receiver);
                    //ins账号更新设备id
                    insAccountInfo.setLastDeviceId(device.getDeviceId());
                    insAccountInfoService.updateInsAccountInfo(insAccountInfo);
                    //设备工作状态更新
                    device.setTaskStatus(1);
                    deviceService.updateDevice(device);
                    wsHandler.sendMessage(session, ConvertGson.toJson(response));
                    return new OperateVO(31);
                } catch (IOException e) {
                    return new OperateVO(10400);
                }
            }
        }
        return new OperateVO(10400);
    }

    @PutMapping("/task/stop/{id}")
    public OperateVO stopTask(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        DeviceDO device = deviceService.getById(id);
        if (device == null) {
            return new OperateVO(10300);
        }
        device.setTaskStatus(0);
        if (deviceService.updateDevice(device)) {
            return new OperateVO(31);
        } else {
            return new OperateVO(10400);
        }
    }

    private InsSendUserInfoDO getReceive(List<InsSendUserInfoDO> receiverList) {
        for (InsSendUserInfoDO receiver : receiverList) {
            if (receiver.getStatus() == 0) {
                return receiver;
            }
        }
        return null;
    }
}
