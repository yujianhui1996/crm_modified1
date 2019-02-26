package com.comma.fit.fitcrm.market.vo;

import java.util.*;
import com.comma.fit.fitcrm.common.entity.IEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author magang
 */
public class MarketActVo extends IEntity {
	private static final long serialVersionUID = 7194444453996979234L;
	/** id */
	private Integer id;
	/** 活动标题 */
	private String title;
	/** 活动类型 */
	private Integer type;
	/** 关联模板id */
	private Integer templateId;
	/**  */
	private String rule;
	/** 活动开始时间，@JsonFormat校准时区和日期格式 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/** 活动结束时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/** 活动状态 1：在线 2：离线 */
	private Integer status;
	/** 活动规则描述 */
	private String description;
	/** 创建时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 修改时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/** 活动副标题 */
	private String subTitle;
	/** 优惠券id */
	private Integer couponId;
	/** 活动渠道 */
	private Integer actChannelId;
	/** 活动用户1：已注册用户2：新用户 */
	private String actUserType;
	/** 版本号 */
	private Integer version;
	/** uv数量 */
	private Integer uvCount;
	/** 注册数量 */
	private Integer registeredCount;
	/** 下单数量 */
	private Integer orderCount;
	/** 创建人 */
	private Integer createUserId;
	/** 修改人 */
	private Integer updateUserId;
	private String actUserTypeArr;
	private MarketChannelVo marketChannelVo;
	private MarketActTemplateVo marketActTemplateVo;
	private String channelName;
	private String templateName;
	private String templateUri;

	public String getTemplateUri() {
		return templateUri;
	}

	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getActUserTypeArr() {
		return actUserTypeArr;
	}

	public void setActUserTypeArr(String actUserTypeArr) {
		this.actUserTypeArr = actUserTypeArr;
	}

	public MarketChannelVo getMarketChannelVo() {
		return marketChannelVo;
	}

	public void setMarketChannelVo(MarketChannelVo marketChannelVo) {
		this.marketChannelVo = marketChannelVo;
	}

	public MarketActTemplateVo getMarketActTemplateVo() {
		return marketActTemplateVo;
	}

	public void setMarketActTemplateVo(MarketActTemplateVo marketActTemplateVo) {
		this.marketActTemplateVo = marketActTemplateVo;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	
	public Integer getTemplateId() {
		return this.templateId;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	public String getRule() {
		return this.rule;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	public String getSubTitle() {
		return this.subTitle;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	
	public Integer getCouponId() {
		return this.couponId;
	}
	public void setActChannelId(Integer actChannelId) {
		this.actChannelId = actChannelId;
	}
	
	public Integer getActChannelId() {
		return this.actChannelId;
	}
	public void setActUserType(String actUserType) {
		this.actUserType = actUserType;
	}
	
	public String getActUserType() {
		return this.actUserType;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}
	public void setUvCount(Integer uvCount) {
		this.uvCount = uvCount;
	}
	
	public Integer getUvCount() {
		return this.uvCount;
	}
	public void setRegisteredCount(Integer registeredCount) {
		this.registeredCount = registeredCount;
	}
	
	public Integer getRegisteredCount() {
		return this.registeredCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	
	public Integer getOrderCount() {
		return this.orderCount;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
	public Integer getCreateUserId() {
		return this.createUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public Integer getUpdateUserId() {
		return this.updateUserId;
	}
	@Override
	public String toString() {
	  return "MarketActVo{" + 
	  			"id:" + getId() +","+
	  			"title:" + getTitle() +","+
	  			"type:" + getType() +","+
	  			"templateId:" + getTemplateId() +","+
	  			"rule:" + getRule() +","+
	  			"startTime:" + getStartTime() +","+
	  			"endTime:" + getEndTime() +","+
	  			"status:" + getStatus() +","+
	  			"description:" + getDescription() +","+
	  			"createTime:" + getCreateTime() +","+
	  			"updateTime:" + getUpdateTime() +","+
	  			"subTitle:" + getSubTitle() +","+
	  			"couponId:" + getCouponId() +","+
	  			"actChannelId:" + getActChannelId() +","+
	  			"actUserType:" + getActUserType() +","+
	  			"version:" + getVersion() +","+
	  			"uvCount:" + getUvCount() +","+
	  			"registeredCount:" + getRegisteredCount() +","+
	  			"orderCount:" + getOrderCount() +","+
	  			"createUserId:" + getCreateUserId() +","+
	  			"updateUserId:" + getUpdateUserId() +
	      '}';
	}

}

