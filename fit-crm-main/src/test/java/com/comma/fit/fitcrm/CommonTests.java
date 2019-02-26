package com.comma.fit.fitcrm;


import com.comma.fit.fitcrm.common.service.ISysDictService;
import com.comma.fit.fitcrm.common.vo.SysDictTypeVo;
import com.comma.fit.fitcrm.common.vo.SysDictVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTests {
    @Autowired
    private ISysDictService sysDictService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTests.class);
    @Test
    @Transactional
    @Rollback
    public void testInsertBatch(){
        SysDictTypeVo sysDictTypeVo=new SysDictTypeVo();
        sysDictTypeVo.setCode("10004");
        sysDictTypeVo.setName("适用用户类型");
        List<SysDictVo> sysDictVoList=new ArrayList<>();
        SysDictVo sysDictVo=new SysDictVo();
        sysDictVo.setName("已注册用户");
        sysDictVo.setValue("1");
        sysDictVo.setCode("10004");
        sysDictVoList.add(sysDictVo);
        sysDictVo=new SysDictVo();
        sysDictVo.setName("新注册用户");
        sysDictVo.setValue("2");
        sysDictVo.setCode("10004");
        sysDictVoList.add(sysDictVo);
        sysDictTypeVo.setSysDictVoList(sysDictVoList);
        //sysDictService.saveSysTypeDict(sysDictTypeVo);
        //Assert.assertEquals(5,count);
    }
}
