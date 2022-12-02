package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.dto.device.CreateOrUpdateDeviceDTO;
import io.github.talelin.latticy.model.BookDO;
import io.github.talelin.latticy.model.DeviceDO;

import java.util.List;

/**
 * 设备服务接口
 */
public interface DeviceService {

    /**
     * 根据设备状态，查询所有设备
     * @param online
     * @return
     */
    List<DeviceDO> getAllDeviceByOnline(Integer online);

    /**
     * 根据id获取设备信息
     * @param id
     * @return
     */
    DeviceDO getById(Integer id);

    /**
     * 根据设备id获取设备信息
     * @param deviceId
     * @return
     */
    DeviceDO getByDeviceId(String deviceId);

    /**
     * 更新设备
     * @param device 设备对象
     * @return 是否更新成功
     */
    boolean updateDevice(DeviceDO device,CreateOrUpdateDeviceDTO validator);

    /**
     * 新增设备
     * @return 是否成功
     */
    boolean createDevice(CreateOrUpdateDeviceDTO validator);

    /**
     * 查询所有设备
     * @return
     */
    List<DeviceDO> findAll();

    /**
     * 删除设备
     * @return 是否删除成功
     */
    boolean deleteById(Integer id);
}
