package com.comma.fit.fitcrm.market.vo;

import com.comma.fit.fitcrm.common.entity.IEntity;

/**
 * @author magang
 */
public class MarketActTemplateVo extends IEntity {
	private static final long serialVersionUID = 6854852093544635304L;
	/**  */
	private Integer id;
	/** 模板地址 */
	private String uri;
	/** 模板名称 */
	private String templateName;


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getUri() {
		return this.uri;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public String getTemplateName() {
		return this.templateName;
	}
	@Override
	public String toString() {
	  return "MarketActTemplateVo{" + 
	  			"id:" + getId() +","+
	  			"uri:" + getUri() +","+
	  			"templateName:" + getTemplateName() +
	      '}';
	}

}

