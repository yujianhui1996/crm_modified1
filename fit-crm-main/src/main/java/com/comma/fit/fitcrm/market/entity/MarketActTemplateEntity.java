package com.comma.fit.fitcrm.market.entity;

/**
 * @author magang
 */
public class MarketActTemplateEntity{
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

}

