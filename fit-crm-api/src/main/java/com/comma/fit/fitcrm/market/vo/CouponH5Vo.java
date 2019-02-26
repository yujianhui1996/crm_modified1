package com.comma.fit.fitcrm.market.vo;

import com.comma.fit.fitcrm.common.entity.IEntity;

import java.util.Date;
import java.util.List;

/**
 * @author magang
 */
public class CouponH5Vo extends IEntity {
    private static final long serialVersionUID = 1601880659013517867L;
    /**
     *
     */
    private Integer id;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 适用类型：1 全部，2 团课，3 私教课
     */
    private String aimName;
    /**
     * 抵扣金额，单位：元
     */
    private Double price;
    private Integer validDays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getAimName() {
        return aimName;
    }

    public void setAimName(String aimName) {
        this.aimName = aimName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }
}

