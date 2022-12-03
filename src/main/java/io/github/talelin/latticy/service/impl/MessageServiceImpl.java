package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.message.CreateOrUpdateMessageDTO;
import io.github.talelin.latticy.mapper.MessageMapper;
import io.github.talelin.latticy.model.MessageDO;
import io.github.talelin.latticy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean createMessage(CreateOrUpdateMessageDTO validator) {
        MessageDO message = new MessageDO();
        message.setContent(validator.getContent());
        message.setImgUrl(validator.getImgUrl());
        return messageMapper.insert(message) > 0;
    }

    @Override
    public boolean updateMessage(MessageDO message, CreateOrUpdateMessageDTO validator) {
        message.setContent(validator.getContent());
        message.setImgUrl(validator.getImgUrl());
        return messageMapper.updateById(message) > 0;
    }

    @Override
    public List<MessageDO> findAll() {
        return messageMapper.selectList(null);
    }

    @Override
    public MessageDO getById(Integer id) {
        return messageMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return messageMapper.deleteById(id) > 0;
    }
}
