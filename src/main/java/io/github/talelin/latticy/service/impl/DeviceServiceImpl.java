package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.dto.device.CreateOrUpdateDeviceDTO;
import io.github.talelin.latticy.mapper.DeviceMapper;
import io.github.talelin.latticy.model.BookDO;
import io.github.talelin.latticy.model.DeviceDO;
import io.github.talelin.latticy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备接口实现类
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper mDeviceMapper;

    @Override
    public List<DeviceDO> getAllDeviceByOnline(Integer online) {
        return mDeviceMapper.getDeviceByOnline(online);
    }

    @Override
    public DeviceDO getById(Integer id) {
        return mDeviceMapper.selectById(id);
    }

    @Override
    public DeviceDO getByDeviceId(String deviceId) {
        return mDeviceMapper.getByDeviceId(deviceId);
    }

    @Override
    public boolean updateDevice(DeviceDO device,CreateOrUpdateDeviceDTO validator) {
        device.setDeviceTag(validator.getDeviceTag());
        device.setOnline(validator.getOnline());
        device.setVersion(validator.getVersion());
        device.setTaskStatus(validator.getTaskStatus());
        return mDeviceMapper.updateById(device)>0;
    }

    @Override
    public boolean createDevice(CreateOrUpdateDeviceDTO validator) {
        DeviceDO device = new DeviceDO();
        device.setDeviceId(validator.getDeviceId());
        device.setDeviceTag(validator.getDeviceTag());
        device.setOnline(0);
        device.setTaskStatus(0);
        device.setVersion(1);
        return mDeviceMapper.insert(device) > 0;
    }

    @Override
    public List<DeviceDO> findAll() {
        return mDeviceMapper.selectList(null);
    }

    @Override
    public boolean deleteById(Integer id) {
        return mDeviceMapper.deleteById(id) > 0;
    }
}
