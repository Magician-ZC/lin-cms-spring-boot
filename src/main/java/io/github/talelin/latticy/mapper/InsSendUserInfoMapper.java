package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.InsSendUserInfoDO;
import org.springframework.stereotype.Repository;

/**
 * 发送信息对象Mapper
 */
@Repository
public interface InsSendUserInfoMapper extends BaseMapper<InsSendUserInfoDO> {

    /**
     * 根据用户名查询发送对象是否存在
     * @param username
     * @return
     */
    int selectCountByUsername(String username);
}
