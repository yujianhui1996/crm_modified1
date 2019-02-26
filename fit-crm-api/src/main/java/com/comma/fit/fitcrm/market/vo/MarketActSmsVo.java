package com.comma.fit.fitcrm.market.vo;

import com.comma.fit.fitcrm.common.entity.IEntity;

/**
 * User: magang
 * Date: 2019-01-22
 * Time: 18:21
 *
 * @author magang
 */
public class MarketActSmsVo extends IEntity {
    private static final long serialVersionUID = -7072092077317341106L;
    private String tel;
    private String timeStamp;
    private Integer couponId;
    private Integer actId;
    private String validNum;
    public String getValidNum() {
        return validNum;
    }

    public void setValidNum(String validNum) {
        this.validNum = validNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }
}
