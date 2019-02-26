package com.comma.fit.fitcrm.market.mapper;

import java.util.List;
import java.util.Map;

import com.comma.fit.fitcrm.market.entity.CourseInfoEntity;
import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.market.entity.CouponDetailEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author comma
 */
public interface ICouponDetailMapper {

    public List<CouponDetailEntity> query(CouponDetailEntity couponDetailEntity);

    public int queryCountByUserIdAndType(@Param("userId") Integer userId, @Param("couponType") Integer couponType, @Param("identityAttribute") Integer identityAttribute);

    public int queryCountByUserIdAndCouponId(@Param("userId") Integer userId, @Param("couponId") Integer couponId);

    public int queryCountByInviteUserId(@Param("userId") Integer userId);

    public int save(CouponDetailEntity couponDetail);

    public CouponDetailEntity selectById(Integer id);

    public int update(CouponDetailEntity couponDetail);

    public int editFailureCouponStatus(@Param("status") String status);

    public int delete(CouponDetailEntity couponDetail);

    String queryGymNameById(@Param("gymId") Integer gymId);

    Integer queryOrderIdByNo(@Param("orderNo") String orderNo);

    CourseInfoEntity queryCourseInfoByCourseProductManageId(@Param("courseProductManageId") Integer courseProductManageId);

    List<CouponDetailEntity> queryFailureCouponDetail();
}
