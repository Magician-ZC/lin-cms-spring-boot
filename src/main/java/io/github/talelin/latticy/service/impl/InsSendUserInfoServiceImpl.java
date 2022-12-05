package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.receive.CreateOrUpdateReceiveDTO;
import io.github.talelin.latticy.mapper.InsSendUserInfoMapper;
import io.github.talelin.latticy.model.InsSendUserInfoDO;
import io.github.talelin.latticy.service.InsSendUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsSendUserInfoServiceImpl implements InsSendUserInfoService {

    @Autowired
    private InsSendUserInfoMapper mInsSendUserInfoMapper;

    @Override
    public boolean createInsAccount(CreateOrUpdateReceiveDTO validator) {
        InsSendUserInfoDO receive = new InsSendUserInfoDO();
        receive.setUsername(validator.getUsername());
        receive.setStatus(0);
        return mInsSendUserInfoMapper.insert(receive) > 0;
    }

    @Override
    public boolean updateInsAccount(InsSendUserInfoDO insSendUserInfo, CreateOrUpdateReceiveDTO validator) {
        insSendUserInfo.setUsername(validator.getUsername());
        return mInsSendUserInfoMapper.updateById(insSendUserInfo)>0;
    }

    @Override
    public boolean updateInsAccountStatus(InsSendUserInfoDO insSendUserInfo) {
        return mInsSendUserInfoMapper.updateById(insSendUserInfo)>0;
    }

    @Override
    public List<InsSendUserInfoDO> findAll() {
        return mInsSendUserInfoMapper.selectList(null);
    }

    @Override
    public InsSendUserInfoDO selectByUsername(String username) {
        return mInsSendUserInfoMapper.selectByUsername(username);
    }

    @Override
    public boolean isExistReceive(String username) {
        return mInsSendUserInfoMapper.selectCountByUsername(username) > 0;
    }

    @Override
    public InsSendUserInfoDO getById(Integer id) {
        return mInsSendUserInfoMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return mInsSendUserInfoMapper.deleteById(id) > 0;
    }
}
