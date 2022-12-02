package io.github.talelin.latticy.service;


import io.github.talelin.latticy.model.InsAccountInfoDO;

import java.util.List;

/**
 * 账号信息服务接口
 */
public interface InsAccountInfoService {

    /**
     * 查询所有账号信息
     * @return
     */
    List<InsAccountInfoDO> findAll();
}
