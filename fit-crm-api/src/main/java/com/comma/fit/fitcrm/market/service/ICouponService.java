package com.comma.fit.fitcrm.market.service;

import com.comma.fit.fitcrm.market.vo.CouponDetailVo;
import com.comma.fit.fitcrm.market.vo.CouponH5Vo;
import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.market.vo.CouponVo;
import com.comma.fit.fitcrm.exception.CommonBizException;

import java.util.List;

public interface ICouponService {
    public PageInfo<CouponVo> query(CouponVo couponVo, int pageNo, int pageSize);

    public void saveCoupon(CouponVo couponVo) throws CommonBizException;

    List<CouponDetailVo> queryCouponDetailList(int userId) throws CommonBizException;

    /**
     * 获取优惠券明细详情
     *
     * @param userId
     * @param couponDetailId
     * @return
     * @throws CommonBizException
     */
    CouponDetailVo querySingleCouponDetail(Integer userId, int couponDetailId) throws CommonBizException;

    PageInfo<CouponDetailVo> queryCouponDetailList(CouponDetailVo couponDetailVo, int pageNo, int pageSize);

    public void editCoupon(CouponVo couponVo) throws CommonBizException;

    void shelvesCoupon(int couponId) throws CommonBizException;

    CouponVo queryById(CouponVo couponVo);

    void receiveCourseCoupon(int userId, int identityAttribute) throws CommonBizException;

    void receiveSubstituteMoneyCoupon(int userId, int couponId) throws CommonBizException;

    void newUserReceiveCoupon(int userId) throws CommonBizException;

    /**
     * 使用优惠券
     *
     * @param userId 用户id
     * @param couponDetailId 优惠券明细id
     * @param gymId 场馆id
     * @param courseProductManageId   排期id
     * @param orderNo 订单编号
     */
    void useCouponDetail(Integer userId, Integer couponDetailId, Integer gymId, Integer courseProductManageId,String orderNo) throws CommonBizException;

    List<CouponH5Vo> selectH5CouponList(Integer userId, Integer couponId, boolean isNewUser) throws CommonBizException;

    Integer editFailureCouponStatus();
}
