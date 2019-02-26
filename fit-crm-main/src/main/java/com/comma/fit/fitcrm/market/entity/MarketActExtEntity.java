package com.comma.fit.fitcrm.market.entity;

/**
 * @author magang
 */
public class MarketActExtEntity extends MarketActEntity {
    private String couponName;
    private String validDays;
    private String aimName;
    private String aimId;
    private String timeStamp;
    private double couponPrice;

    public double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getAimId() {
        return aimId;
    }

    public void setAimId(String aimId) {
        this.aimId = aimId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getValidDays() {
        return validDays;
    }

    public void setValidDays(String validDays) {
        this.validDays = validDays;
    }

    public String getAimName() {
        return aimName;
    }

    public void setAimName(String aimName) {
        this.aimName = aimName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

