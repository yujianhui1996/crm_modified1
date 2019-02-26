package com.comma.fit.fitcrm.common.vo;

import java.util.*;
import com.comma.fit.fitcrm.common.entity.IEntity;
/**
 * @author magang
 */
public class SysDictTypeVo extends IEntity{
	private static final long serialVersionUID = 3920216871355613782L;
	/**  */
	private Integer id;
	/** 字典类型编码 */
	private String code;
	/** 字典类型名称 */
	private String name;
	private List<SysDictVo> sysDictVoList;

	public List<SysDictVo> getSysDictVoList() {
		return sysDictVoList;
	}

	public void setSysDictVoList(List<SysDictVo> sysDictVoList) {
		this.sysDictVoList = sysDictVoList;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	@Override
	public String toString() {
	  return "SysDictTypeEntity{" + 
	  			"id:" + getId() +","+
	  			"code:" + getCode() +","+
	  			"name:" + getName() +
	      '}';
	}

}

