package com.comma.fit.fitcrm.market.vo;

import java.util.*;
import com.comma.fit.fitcrm.common.entity.IEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author magang
 */
public class CouponVo extends IEntity {
	private static final long serialVersionUID = 1601880659013517867L;
	/**  */
	private Integer id;
	/**  */
	private Integer equityId;
	/** 优惠券名称 */
	private String name;
	/** 优惠券描述 */
	private String description;
	/** 适用类型：1 全部，2 团课，3 私教课 */
	private String aim;
	/** 领取数量限制 */
	private Integer limitNum;
	/** 开始时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/** 结束时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/** 有效天数 */
	private Integer validDays;
	/** 领取数量 */
	private Integer receiveNum;
	/** 发放渠道：1：关联活动 */
	private Integer issueChannel;
	/** 优惠券类型：1 代金券 2.新人体验区 3.券程体验区 */
	private Integer couponType;
	/** 适用用户：1 全部，2 会员，3 注册用户，4 指定用户 */
	private Integer userType;
	/** 生效类型：1 领取后生效，2 固定时间生效 */
	private Integer validType;
	/** 适用场馆：1 全部场馆，2 部分场馆 */
	private Integer gymType;
	/** 操作人id */
	private Integer operateId;
	/** 操作人名称 */
	private String operateName;
	/** 创建时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 更新时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/** 使用数量 */
	private Integer useNum;
	/** 更新人名称 */
	private String updateName;
	/** 更新人id */
	private Integer updateId;
	/** 抵扣金额，单位：元 */
	private Double price;
	/** 满足价格 */
	private Double satisfyPrice;
	/** 发放数量 */
	private Integer issueNum;
	/** 版本号 */
	private Integer version;
	/** 状态：1：上架 2：下架 */
	private Integer status;
	private String aimName;
	private String couponTypeName;
	private String gymTypeName;
	private String statusName;

	private String issueChannelName;
	private List<String> aimArr;

	public String getIssueChannelName() {
		return issueChannelName;
	}

	public void setIssueChannelName(String issueChannelName) {
		this.issueChannelName = issueChannelName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getGymTypeName() {
		return gymTypeName;
	}

	public void setGymTypeName(String gymTypeName) {
		this.gymTypeName = gymTypeName;
	}

	public String getCouponTypeName() {
		return couponTypeName;
	}

	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
	}

	public String getAimName() {
		return aimName;
	}

	public void setAimName(String aimName) {
		this.aimName = aimName;
	}

	public List<String> getAimArr() {
		return aimArr;
	}

	public void setAimArr(List<String> aimArr) {
		this.aimArr = aimArr;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setEquityId(Integer equityId) {
		this.equityId = equityId;
	}
	
	public Integer getEquityId() {
		return this.equityId;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
	
	public String getAim() {
		return this.aim;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	
	public Integer getLimitNum() {
		return this.limitNum;
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
	public void setValidDays(Integer validDays) {
		this.validDays = validDays;
	}
	
	public Integer getValidDays() {
		return this.validDays;
	}
	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}
	
	public Integer getReceiveNum() {
		return this.receiveNum;
	}
	public void setIssueChannel(Integer issueChannel) {
		this.issueChannel = issueChannel;
	}
	
	public Integer getIssueChannel() {
		return this.issueChannel;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	
	public Integer getCouponType() {
		return this.couponType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getUserType() {
		return this.userType;
	}
	public void setValidType(Integer validType) {
		this.validType = validType;
	}
	
	public Integer getValidType() {
		return this.validType;
	}
	public void setGymType(Integer gymType) {
		this.gymType = gymType;
	}
	
	public Integer getGymType() {
		return this.gymType;
	}
	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}
	
	public Integer getOperateId() {
		return this.operateId;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	
	public String getOperateName() {
		return this.operateName;
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
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	
	public Integer getUseNum() {
		return this.useNum;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	public String getUpdateName() {
		return this.updateName;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	
	public Integer getUpdateId() {
		return this.updateId;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setSatisfyPrice(Double satisfyPrice) {
		this.satisfyPrice = satisfyPrice;
	}
	
	public Double getSatisfyPrice() {
		return this.satisfyPrice;
	}
	public void setIssueNum(Integer issueNum) {
		this.issueNum = issueNum;
	}
	
	public Integer getIssueNum() {
		return this.issueNum;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	@Override
	public String toString() {
	  return "CouponVo{" + 
	  			"id:" + getId() +","+
	  			"equityId:" + getEquityId() +","+
	  			"name:" + getName() +","+
	  			"description:" + getDescription() +","+
	  			"aim:" + getAim() +","+
	  			"limitNum:" + getLimitNum() +","+
	  			"startTime:" + getStartTime() +","+
	  			"endTime:" + getEndTime() +","+
	  			"validDays:" + getValidDays() +","+
	  			"receiveNum:" + getReceiveNum() +","+
	  			"issueChannel:" + getIssueChannel() +","+
	  			"couponType:" + getCouponType() +","+
	  			"userType:" + getUserType() +","+
	  			"validType:" + getValidType() +","+
	  			"gymType:" + getGymType() +","+
	  			"operateId:" + getOperateId() +","+
	  			"operateName:" + getOperateName() +","+
	  			"createTime:" + getCreateTime() +","+
	  			"updateTime:" + getUpdateTime() +","+
	  			"useNum:" + getUseNum() +","+
	  			"updateName:" + getUpdateName() +","+
	  			"updateId:" + getUpdateId() +","+
	  			"price:" + getPrice() +","+
	  			"satisfyPrice:" + getSatisfyPrice() +","+
	  			"issueNum:" + getIssueNum() +","+
	  			"version:" + getVersion() +","+
	  			"status:" + getStatus() +
	      '}';
	}

}

