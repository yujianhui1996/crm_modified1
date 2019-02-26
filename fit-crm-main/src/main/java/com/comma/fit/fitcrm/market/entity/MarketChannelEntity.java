package com.comma.fit.fitcrm.market.entity;

import java.util.*;
/**
 * @author magang
 */
public class MarketChannelEntity{
	/**  */
	private Integer id;
	/** 渠道名称 */
	private String channelName;
	/** 渠道等级 */
	private Integer channelLevel;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Integer createUserId;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人 */
	private Integer updateUserId;


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	public String getChannelName() {
		return this.channelName;
	}
	public void setChannelLevel(Integer channelLevel) {
		this.channelLevel = channelLevel;
	}
	
	public Integer getChannelLevel() {
		return this.channelLevel;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public Integer getUpdateUserId() {
		return this.updateUserId;
	}

}

