package io.github.talelin.latticy.service.impl;

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
    public List<InsAccountInfoDO> findAll() {
        return mInsAccountInfoMapper.selectList(null);
    }
}
