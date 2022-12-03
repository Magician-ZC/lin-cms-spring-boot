package io.github.talelin.latticy.service;


import io.github.talelin.latticy.dto.account.CreateOrUpdateAccountDTO;
import io.github.talelin.latticy.dto.message.CreateOrUpdateMessageDTO;
import io.github.talelin.latticy.model.BookDO;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.model.MessageDO;

import java.util.List;

/**
 * 账号信息服务接口
 */
public interface InsAccountInfoService {

    /**
     * 新增ins账号模板
     * @param validator 数据传输对象
     * @return 是否成功
     */
    boolean createInsAccount(CreateOrUpdateAccountDTO validator);

    /**
     * 更新ins账号模板
     * @param insAccountInfo ins账号对象
     * @param validator 数据传输对象
     * @return 是否更新成功
     */
    boolean updateInsAccount(InsAccountInfoDO insAccountInfo, CreateOrUpdateAccountDTO validator);

    /**
     * 查询所有账号信息
     * @return
     */
    List<InsAccountInfoDO> findAll();

    /**
     * 根据用户名查询该账号是否存在
     * @param username
     * @return
     */
    boolean isExistInsAccount(String username);

    /**
     * 获取ins账号
     * @param id 主键id
     * @return ins账号数据对象
     */
    InsAccountInfoDO getById(Integer id);


    /**
     * 删除ins账号
     * @param id 主键id
     * @return 是否删除成功
     */
    boolean deleteById(Integer id);
}
