package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.DeviceDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import org.springframework.stereotype.Repository;

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
}
