package com.comma.fit.fitcrm.market.mapper;

import com.comma.fit.fitcrm.market.entity.CouponEntity;
import java.util.List;

public interface ICouponMapper {

    public List<CouponEntity> query(CouponEntity coupon);

    public List<CouponEntity> selectCouponOrNewUserByCouponId(CouponEntity coupon);

    public int save(CouponEntity coupon);

    public CouponEntity selectById(Integer id);

    public int update(CouponEntity coupon);

    public int delete(CouponEntity coupon);
}
