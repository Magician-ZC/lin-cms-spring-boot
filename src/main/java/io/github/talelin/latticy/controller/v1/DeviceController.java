package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.dto.device.CreateOrUpdateDeviceDTO;
import io.github.talelin.latticy.model.DeviceDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.service.DeviceService;
import io.github.talelin.latticy.service.InsAccountInfoService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.OperateVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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

    @PostMapping("/{id}")
    public OperateVO startTask(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        //获取当前设备信息
        DeviceDO device = deviceService.getById(id);
        //获取账号信息
        List<InsAccountInfoDO> insAccountInfoList = insAccountInfoService.findAll();
        for (int i = 0; i < insAccountInfoList.size(); i++) {
            InsAccountInfoDO insAccountInfo = insAccountInfoList.get(i);
            //账号信息正常进入下一步,否则继续下一行
            if (insAccountInfo.getStatus() == 0) {
                //如果账号信息上一次被发送给该设备或者该账号信息没有被使用过,继续发送给该设备
                if (insAccountInfo.getLastDeviceId() == null || device.getDeviceId() == insAccountInfo.getLastDeviceId()) {
                    //继续给这台设备发送指令
                    break;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }

        return new OperateVO(19);
    }
}
