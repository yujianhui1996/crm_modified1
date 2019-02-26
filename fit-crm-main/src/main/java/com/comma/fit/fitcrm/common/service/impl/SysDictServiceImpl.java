package com.comma.fit.fitcrm.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.comma.fit.fitcrm.common.constant.CommonConstant;
import com.comma.fit.fitcrm.common.entity.SysDictTypeEntity;
import com.comma.fit.fitcrm.common.mapper.ISysDictTypeMapper;
import com.comma.fit.fitcrm.common.vo.SysDictTypeVo;
import com.comma.fit.fitcrm.exception.CommonBizException;
import com.comma.fit.fitcrm.exception.ExpCodeEnum;
import com.comma.fit.fitcrm.common.vo.SysDictVo;
import com.comma.fit.fitcrm.common.entity.SysDictEntity;
import com.comma.fit.fitcrm.common.mapper.ISysDictMapper;
import com.comma.fit.fitcrm.common.service.ISysDictService;
import com.comma.fit.fitcrm.market.entity.MarketActEntity;
import com.comma.fit.fitcrm.market.vo.MarketActVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.comma.fit.fitcrm.util.FitBeanCopyUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author magang
 */
@org.springframework.stereotype.Service
@Service(version = CommonConstant.VERSION)
@Transactional(rollbackFor = CommonBizException.class)
public class SysDictServiceImpl implements ISysDictService {
	private static final Logger logger = LoggerFactory.getLogger(SysDictServiceImpl.class);
	@Autowired
	private ISysDictMapper sysDictMapper;
	@Autowired
	private ISysDictTypeMapper sysDictTypeMapper;

	@Override
	public void saveSysTypeDict(SysDictTypeVo sysDictTypeVo) throws CommonBizException {
		SysDictTypeEntity sysDictTypeEntity=new SysDictTypeEntity();
		BeanUtil.copyProperties(sysDictTypeVo, sysDictTypeEntity);
		int result = sysDictTypeMapper.save(sysDictTypeEntity);
		if (result != 1) {
			throw new CommonBizException(ExpCodeEnum.COMMON_DICT_SAVE_ERROR);
		}
		for (SysDictVo sysDictVo : sysDictTypeVo.getSysDictVoList()) {
			SysDictEntity sysDictEntity=new SysDictEntity();
			BeanUtil.copyProperties(sysDictVo, sysDictEntity);
			sysDictMapper.save(sysDictEntity);
		}
	}

	@Override
	public PageInfo<SysDictVo> query(SysDictVo sysDictVo,int pageNo,int pageSize) {
		SysDictEntity sysDictEntity=new SysDictEntity();
		BeanUtil.copyProperties(sysDictVo, sysDictEntity);
		PageHelper.startPage(pageNo, pageSize);
		List<SysDictEntity> sysDictEntityList = sysDictMapper.query(sysDictEntity);
		try {
			PageInfo<SysDictEntity> sysDictEntityPageInfo=new PageInfo<>(sysDictEntityList);
			PageInfo<SysDictVo> page = FitBeanCopyUtil.pageInfoCopy(sysDictEntityPageInfo, SysDictVo.class);
			return page;
		} catch (Exception e) {
			logger.error("voList copy to entityList error", e);
		}
		return null;	
	}

	/**
	 * 根据字典编号获取字典列表
	 * @param code
	 * @return
	 */
	@Override
	public List<SysDictVo> queryByCode(String code){
		SysDictEntity sysDictEntity=new SysDictEntity();
		sysDictEntity.setCode(code);
		List<SysDictEntity> sysDictEntityList = sysDictMapper.query(sysDictEntity);
		List<SysDictVo> sysDictVoList=null;
		try {
			sysDictVoList = FitBeanCopyUtil.listBeanCopy(sysDictEntityList, SysDictVo.class);
		} catch (Exception e) {
			logger.error("voList copy to entityList error", e);
		}
		return sysDictVoList;
	}

    /**
     * 根据字典编号获取字典map集合
     * key：字典值
     * value：字典对象
     * @param code
     * @return
     */
    @Override
    public Map<String,SysDictVo> queryDictMapByCode(String code){
        Map<String,SysDictVo> map=new HashMap<>();
        SysDictEntity sysDictEntity=new SysDictEntity();
        sysDictEntity.setCode(code);
        List<SysDictEntity> sysDictEntityList = sysDictMapper.query(sysDictEntity);
        List<SysDictVo> sysDictVoList=null;
        try {
            sysDictVoList = FitBeanCopyUtil.listBeanCopy(sysDictEntityList, SysDictVo.class);
            for (SysDictVo sysDictVo : sysDictVoList) {
                map.put(sysDictVo.getValue(),sysDictVo);
            }
        } catch (Exception e) {
            logger.error("voList copy to entityList error", e);
        }
        return map;
    }

	@Override
	public void editSysDict(SysDictVo sysDictVo) {
		SysDictEntity sysDictEntity = new SysDictEntity();
		BeanUtil.copyProperties(sysDictVo, sysDictEntity);
		int result = sysDictMapper.update(sysDictEntity);
		if (result != 1) {
			throw new CommonBizException(ExpCodeEnum.COMMON_DICT_EDIT_ERROR);
		}
	}

	@Override
	public SysDictVo queryById(SysDictVo sysDictVo) {
		SysDictEntity sysDictEntity = sysDictMapper.selectById(sysDictVo.getId());
		BeanUtil.copyProperties(sysDictEntity, sysDictVo);
		System.out.println(new Gson().toJson(sysDictVo));
		return sysDictVo;
    }

    @Override
    public String queryDictNameByGsonValue(String gsonValue, Map<String, SysDictVo> map){
    	if (StrUtil.isEmpty(gsonValue)){
    		return null;
		}
		List<String> aimTypeList = new Gson().fromJson(gsonValue, new TypeToken<List<String>>() {
		}.getType());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < aimTypeList.size(); i++) {
			sb.append(map.get(aimTypeList.get(i)).getName());
			if (i != (aimTypeList.size() - 1)) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
}