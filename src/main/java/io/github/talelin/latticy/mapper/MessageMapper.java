package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.MessageDO;
import org.springframework.stereotype.Repository;

/**
 * 发送信息Mapper
 */
@Repository
public interface MessageMapper extends BaseMapper<MessageDO> {
}
