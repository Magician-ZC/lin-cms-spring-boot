package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.account.CreateOrUpdateAccountDTO;
import io.github.talelin.latticy.mapper.InsAccountInfoMapper;
import io.github.talelin.latticy.model.InsAccountInfoDO;
import io.github.talelin.latticy.service.InsAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsAccountInfoServiceImpl implements InsAccountInfoService {

    @Autowired
    private InsAccountInfoMapper mInsAccountInfoMapper;

    @Override
    public boolean createInsAccount(CreateOrUpdateAccountDTO validator) {
        InsAccountInfoDO insAccount = new InsAccountInfoDO();
        insAccount.setUsername(validator.getUsername());
        insAccount.setPassword(validator.getPassword());
        insAccount.setStatus(0);
        insAccount.setCount(0);
        return mInsAccountInfoMapper.insert(insAccount) > 0;
    }

    @Override
    public boolean updateInsAccount(InsAccountInfoDO insAccountInfo, CreateOrUpdateAccountDTO validator) {
        insAccountInfo.setUsername(validator.getUsername());
        insAccountInfo.setPassword(validator.getPassword());
        return mInsAccountInfoMapper.updateById(insAccountInfo) > 0;
    }

    @Override
    public boolean updateInsAccountInfo(InsAccountInfoDO insAccountInfo) {
        return mInsAccountInfoMapper.updateById(insAccountInfo) > 0;
    }

    @Override
    public List<InsAccountInfoDO> findAll() {
        return mInsAccountInfoMapper.selectList(null);
    }

    @Override
    public List<InsAccountInfoDO> selectAllTask() {
        return mInsAccountInfoMapper.selectAllTask();
    }

    @Override
    public List<InsAccountInfoDO> selectAllTaskByDeviceId(String deviceId) {
        return mInsAccountInfoMapper.selectAllTaskByDeviceId(deviceId);
    }

    @Override
    public boolean isExistInsAccount(String username) {
        return mInsAccountInfoMapper.selectCountByUsername(username) > 0;
    }

    @Override
    public InsAccountInfoDO getById(Integer id) {
        return mInsAccountInfoMapper.selectById(id);
    }

    @Override
    public InsAccountInfoDO selectByUsername(String username) {
        return mInsAccountInfoMapper.selectByUsername(username);
    }

    @Override
    public boolean deleteById(Integer id) {
        return mInsAccountInfoMapper.deleteById(id)>0;
    }
}
