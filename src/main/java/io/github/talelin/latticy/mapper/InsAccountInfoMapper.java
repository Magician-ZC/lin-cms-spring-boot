package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.DeviceDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号信息Mapper
 */
@Repository
public interface InsAccountInfoMapper extends BaseMapper<InsAccountInfoDO> {

    /**
     * 根据用户名查询账户信息
     * @param username
     * @return
     */
    int selectCountByUsername(String username);

    /**
     * 根据用户名查询账户信息
     * @param username
     * @return
     */
    InsAccountInfoDO selectByUsername(String username);
    /**
     * 查询所有可以开始任务的账号信息
     * @return
     */
    List<InsAccountInfoDO> selectAllTask();

    /**
     * 查询上一次使用该设备的账号信息
     * @return
     */
    List<InsAccountInfoDO> selectAllTaskByDeviceId(String deviceId);


}
