package com.comma.fit.fitcrm.market.entity;

import java.util.*;
/**
 * @author magang
 */
public class CouponDetailEntity{
	/**  */
	private Integer id;
	/** 优惠券编码 */
	private String code;
	/** 关联优惠券id */
	private Integer couponId;
	/** 优惠券状态 */
	private String status;
	/** 关联用户id */
	private Integer userId;
	/** 优惠券开始时间 */
	private Date startTime;
	/** 优惠券结束时间 */
	private Date endTime;
	/** 领取时间 */
	private Date createTime;
	/** 使用时间 */
	private Date useTime;
	/** 场馆id */
	private Integer gymId;
	/** 场馆名称 */
	private String gymName;
	/** 商品id */
	private Integer productId;
	/** 商品名称 */
	private String productName;
	/** 订单id */
	private Integer orderId;
	/** 订单编号 */
	private String orderNo;
	/** 版本号 */
	private Integer version;
	/** 身份属性 1：邀请者 2：被邀请者 */
	private Integer identityAttribute;

	private String phone;
	private Integer couponType;
	private String couponName;
	private String nickName;
	private String price;
	private String aim;
	private String aimName;

	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public String getAimName() {
		return aimName;
	}

	public void setAimName(String aimName) {
		this.aimName = aimName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	
	public Integer getCouponId() {
		return this.couponId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return this.userId;
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

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	
	public Date getUseTime() {
		return this.useTime;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	
	public Integer getGymId() {
		return this.gymId;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	
	public String getGymName() {
		return this.gymName;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getProductId() {
		return this.productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return this.productName;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getOrderId() {
		return this.orderId;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}
	public void setIdentityAttribute(Integer identityAttribute) {
		this.identityAttribute = identityAttribute;
	}
	
	public Integer getIdentityAttribute() {
		return this.identityAttribute;
	}

}

