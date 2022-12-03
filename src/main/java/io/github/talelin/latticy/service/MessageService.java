package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.dto.message.CreateOrUpdateMessageDTO;
import io.github.talelin.latticy.model.BookDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.model.MessageDO;

import java.util.List;

/**
 * 信息模板服务接口
 */
public interface MessageService {

    /**
     * 新增信息模板
     * @param validator 数据传输对象
     * @return 是否成功
     */
    boolean createMessage(CreateOrUpdateMessageDTO validator);

    /**
     * 更新信息模板
     * @param message 信息模板对象
     * @param validator 数据传输对象
     * @return 是否更新成功
     */
    boolean updateMessage(MessageDO message, CreateOrUpdateMessageDTO validator);

    /**
     * 查询所有信息模板
     * @return
     */
    List<MessageDO> findAll();

    /**
     * 获取信息内容
     * @param id 主键id
     * @return ins账号数据对象
     */
    MessageDO getById(Integer id);


    /**
     * 删除信息模板
     * @param id 主键id
     * @return 是否删除成功
     */
    boolean deleteById(Integer id);
}
