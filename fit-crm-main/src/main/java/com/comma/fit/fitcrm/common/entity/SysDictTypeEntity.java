package com.comma.fit.fitcrm.common.entity;

import java.util.*;
/**
 * @author magang
 */
public class SysDictTypeEntity{
	/**  */
	private Integer id;
	/** 字典类型编码 */
	private String code;
	/** 字典类型名称 */
	private String name;


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

