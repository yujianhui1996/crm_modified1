package com.comma.fit.fitcrm.market.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.comma.fit.fitcrm.common.constant.CommonConstant;
import com.comma.fit.fitcrm.market.service.IAbcService;

@org.springframework.stereotype.Service//Spring的@Service
@Service(version = CommonConstant.VERSION)//dubbo的@Service
public class AbcServiceImpl implements IAbcService {
    @Override
    public String sayHello() {
        return "hello yujianhui !";
    }
}
