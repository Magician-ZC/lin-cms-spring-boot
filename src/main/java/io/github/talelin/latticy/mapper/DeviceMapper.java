package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.DeviceDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备信息Mapper
 */
@Repository
public interface DeviceMapper extends BaseMapper<DeviceDO> {

    /**
     * @param online
     * @return
     */
    List<DeviceDO> getDeviceByOnline(int online);


    /**
     * 根据设备id查询设备
     * @param deviceId
     * @return
     */
    DeviceDO getByDeviceId(String deviceId);

    @Override
    int updateById(DeviceDO entity);
}
