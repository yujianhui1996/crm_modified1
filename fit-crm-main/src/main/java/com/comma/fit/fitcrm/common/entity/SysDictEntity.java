package com.comma.fit.fitcrm.common.entity;

import java.util.*;
/**
 * @author magang
 */
public class SysDictEntity{
	/**  */
	private Integer id;
	/** 字典类型编码 */
	private String code;
	/** 字典名 */
	private String name;
	/** 字典值 */
	private String value;


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
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	@Override
	public String toString() {
	  return "SysDictEntity{" + 
	  			"id:" + getId() +","+
	  			"code:" + getCode() +","+
	  			"name:" + getName() +","+
	  			"value:" + getValue() +
	      '}';
	}

}

