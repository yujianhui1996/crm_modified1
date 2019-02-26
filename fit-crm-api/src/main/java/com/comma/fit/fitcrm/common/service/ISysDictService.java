package com.comma.fit.fitcrm.common.service;

import com.comma.fit.fitcrm.common.vo.SysDictTypeVo;
import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.common.vo.SysDictVo;
import com.comma.fit.fitcrm.exception.CommonBizException;

import java.util.List;
import java.util.Map;

public interface ISysDictService{
	void saveSysTypeDict(SysDictTypeVo sysDictTypeVo) throws CommonBizException;

	public PageInfo<SysDictVo> query(SysDictVo sysDictVo,int pageNo,int pageSize);

	List<SysDictVo> queryByCode(String code);

    Map<String,SysDictVo> queryDictMapByCode(String code);

    public void editSysDict(SysDictVo sysDictVo) throws CommonBizException;

    SysDictVo queryById(SysDictVo sysDictVo);

    String queryDictNameByGsonValue(String gsonValue, Map<String, SysDictVo> map);
}
