package io.github.talelin.latticy.service;


import io.github.talelin.latticy.model.BookDO;
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
