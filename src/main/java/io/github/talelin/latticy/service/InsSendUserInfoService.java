package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.receive.CreateOrUpdateReceiveDTO;
import io.github.talelin.latticy.model.InsSendUserInfoDO;

import java.util.List;

/**
 * 发送信息对象服务接口
 */
public interface InsSendUserInfoService {
    /**
     * 前端新增发送信息对象
     * @param validator 数据传输对象
     * @return 是否成功
     */
    boolean createInsAccount(CreateOrUpdateReceiveDTO validator);

    /**
     * 后端新增发送信息对象
     * @param insSendUserInfo 发送信息对象
     * @return 是否成功
     */
    boolean createReceive(InsSendUserInfoDO insSendUserInfo);

    /**
     *  前端更新发送信息对象
     * @param insSendUserInfo 发送信息对象
     * @param validator 数据传输对象
     * @return 是否更新成功
     */
    boolean updateInsAccount(InsSendUserInfoDO insSendUserInfo,CreateOrUpdateReceiveDTO validator);

    /**
     * 后端更新发送信息对象状态
     * @param insSendUserInfo 发送信息对象
     * @return 是否更新成功
     */
    boolean updateInsAccountStatus(InsSendUserInfoDO insSendUserInfo);

    /**
     * 查询所有发送对象信息
     * @return
     */
    List<InsSendUserInfoDO> findAll();

    /**
     * 根据用户名查找发送对象信息
     * @return
     */
    InsSendUserInfoDO selectByUsername(String username);

    /**
     * 根据用户名查询该账号是否存在
     * @param username
     * @return
     */
    boolean isExistReceive(String username);

    /**
     * 获取发送信息对象
     * @param id 主键id
     * @return 发送信息对象
     */
    InsSendUserInfoDO getById(Integer id);


    /**
     * 删除发送信息对象
     * @param id 主键id
     * @return 是否删除成功
     */
    boolean deleteById(Integer id);
}
