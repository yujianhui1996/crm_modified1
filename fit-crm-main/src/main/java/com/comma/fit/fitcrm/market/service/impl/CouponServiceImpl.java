package com.comma.fit.fitcrm.market.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.comma.fit.fitcrm.common.constant.CommonConstant;
import com.comma.fit.fitcrm.common.constant.FitConstant;
import com.comma.fit.fitcrm.common.constant.SysDictConstant;
import com.comma.fit.fitcrm.common.service.ISysDictService;
import com.comma.fit.fitcrm.common.vo.SysDictVo;
import com.comma.fit.fitcrm.exception.CommonBizException;
import com.comma.fit.fitcrm.exception.ExpCodeEnum;
import com.comma.fit.fitcrm.market.entity.CouponDetailEntity;
import com.comma.fit.fitcrm.market.entity.CourseInfoEntity;
import com.comma.fit.fitcrm.market.mapper.ICouponDetailMapper;
import com.comma.fit.fitcrm.market.vo.CouponDetailVo;
import com.comma.fit.fitcrm.market.vo.CouponH5Vo;
import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.market.vo.CouponVo;
import com.comma.fit.fitcrm.market.entity.CouponEntity;
import com.comma.fit.fitcrm.market.mapper.ICouponMapper;
import com.comma.fit.fitcrm.market.service.ICouponService;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imadcn.framework.idworker.generator.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;//spring自动注入
import com.comma.fit.fitcrm.util.FitBeanCopyUtil;//复制bean功能类
import org.springframework.transaction.annotation.Transactional;

/**
 * @author magang
 */
@org.springframework.stereotype.Service//Spring的@Service
@Service(version = CommonConstant.VERSION)//dubbo的@Service
@Transactional(rollbackFor = CommonBizException.class)
public class CouponServiceImpl implements ICouponService {
    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);
    @Autowired
    private ICouponMapper couponMapper;
    @Autowired
    private ICouponDetailMapper couponDetailMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 保存优惠券
     *
     * @param couponVo
     * @throws CommonBizException
     */
    @Override
    public void saveCoupon(CouponVo couponVo) throws CommonBizException {
        logger.info("保存优惠券：请求参数：{}", new Gson().toJson(couponVo));//将参数vo转为json形式记录日志
        if (couponVo.getIssueNum() == null) {
            logger.info("优惠券发放数量为空！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COURSE_ISSUE_NUM_ERROR);
        }
        if (couponVo.getAimArr() == null) {
            logger.info("优惠券适用类型为空！");
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }
        CouponEntity couponEntity = new CouponEntity();
        BeanUtil.copyProperties(couponVo, couponEntity);  //bean转bean（相同属性的复制）；拷贝参数vo到新对象entity
        couponEntity.setAim(new Gson().toJson(couponVo.getAimArr()));
        couponEntity.setStartTime(new Date());
        couponEntity.setCreateTime(new Date());
        couponEntity.setValidType(FitConstant.VALID_TYPE_RECEIVE);
        couponEntity.setUpdateTime(new Date());
        couponEntity.setReceiveNum(0);
        couponEntity.setVersion(1);
        couponEntity.setUseNum(0);
        couponEntity.setLimitNum(1);
        couponEntity.setStatus(FitConstant.COUPON_STATUS_ONLINE);
        int result = couponMapper.save(couponEntity); //保存新对象entity
        if (result != 1) {
            logger.info("优惠券保存失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_SAVE_ERROR);
        }
    }

    /**
     * 优惠券列表
     * 分页
     * @param couponVo
     * @return
     */
    @Override
    public PageInfo<CouponVo> query(CouponVo couponVo, int pageNo, int pageSize) {
        logger.info("查询保存优惠券列表：请求参数：{},pageNo：{},pageSize：{}", new Gson().toJson(couponVo), pageNo, pageSize);
        CouponEntity couponEntity = new CouponEntity();//新建entity对象
        BeanUtil.copyProperties(couponVo, couponEntity);//拷贝参数vo到entity
        couponEntity.setCouponType(FitConstant.COUPON_TYPE_VOUCHERS);
        PageHelper.startPage(pageNo, pageSize);//设置分页
        List<CouponEntity> couponEntityList = couponMapper.query(couponEntity);
        try {
            //把查询的结果列表作为参数，创建entity的pageInfo对象
            PageInfo<CouponEntity> couponEntityPageInfo = new PageInfo<>(couponEntityList);
            //利用FitBeanCopyUtil工具类把entity型的pageInfo复制成vo型
            PageInfo<CouponVo> page = FitBeanCopyUtil.pageInfoCopy(couponEntityPageInfo, CouponVo.class);
            //创建字典映射类：类型名-类型值 ，状态名-状态值
            Map<String, SysDictVo> couponTypeMap = sysDictService.queryDictMapByCode(SysDictConstant.COUPON_TYPE);
            Map<String, SysDictVo> statusNameMap = sysDictService.queryDictMapByCode(SysDictConstant.COUPON_STATUS);
            //遍历page对象设置每个vo的couponTypeName，statusName
            for (CouponVo vo : page.getList()) {
                vo.setCouponTypeName(couponTypeMap.get(vo.getCouponType().toString()).getName());
                vo.setStatusName(statusNameMap.get(vo.getStatus().toString()).getName());
            }
            return page;

        } catch (Exception e) {
            logger.error("voList copy to entityList error", e);
        }
        return null;
    }

    /**
     * 获取用户的优惠券列表(小程序查询)
     *
     * @param userId
     * @return
     */
    @Override
    public List<CouponDetailVo> queryCouponDetailList(int userId) {
        logger.info("获取用户的优惠券列表：用户id：{}", userId);
        CouponDetailEntity couponDetailEntity = new CouponDetailEntity();//新建Entity对象
        couponDetailEntity.setUserId(userId);
        couponDetailEntity.setStatus(FitConstant.COUPON_DETAIL_STATUS_INIT);
        List<CouponDetailEntity> couponDetailEntityList = couponDetailMapper.query(couponDetailEntity); //返回查询结果List
        try {
            List<CouponDetailVo> couponDetailVoList = FitBeanCopyUtil.listBeanCopy(couponDetailEntityList, CouponDetailVo.class);//复制list
            //创建映射，适用类型-产品类型值
            Map<String, SysDictVo> map = sysDictService.queryDictMapByCode(SysDictConstant.PRODUCT_TYPE);
            //遍历设置AimName适用类型名，可多值用'，'连接
            for (CouponDetailVo vo : couponDetailVoList) {
                vo.setAimName(sysDictService.queryDictNameByGsonValue(vo.getAim(), map));
            }
            return couponDetailVoList; //返回voList
        } catch (Exception e) {
            logger.error("voList copy to entityList error", e);
        }
        return null;
    }

    /**
     * 获取用户的优惠券明细
     *
     * @param userId
     * @return
     */
    @Override
    public CouponDetailVo querySingleCouponDetail(Integer userId, int couponDetailId) throws CommonBizException {
        logger.info("获取用户的优惠券单个明细详情：用户id：{},优惠券详情id：{}", userId, couponDetailId);
        CouponDetailEntity couponDetailEntity = couponDetailMapper.selectById(couponDetailId);//以couponId查询
        CouponDetailVo couponDetailVo = new CouponDetailVo();
        if (couponDetailEntity == null) {
            logger.info("优惠券明细数据不存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_NOT_EXISTS_ERROR);
        }
        if (!userId.equals(couponDetailEntity.getUserId())) {
            logger.info("该优惠券不属于该用户！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_NOT_BELONG_USER);
        }
        BeanUtil.copyProperties(couponDetailEntity, couponDetailVo); //把entity拷贝到vo并返回
        return couponDetailVo;
    }

    /**
     * 优惠券券码核销查询(sass查询)
     * 分页
     * @param couponDetailVo
     * @return
     */
    @Override
    public PageInfo<CouponDetailVo> queryCouponDetailList(CouponDetailVo couponDetailVo, int pageNo, int pageSize) {
        logger.info("优惠券券码核销查询：请求参数{},pageNo：{},pageSize：{}", new Gson().toJson(couponDetailVo), pageNo, pageSize);
        CouponDetailEntity couponDetailEntity = new CouponDetailEntity();
        BeanUtil.copyProperties(couponDetailVo, couponDetailEntity);//把参数vo复制到entity
        PageHelper.startPage(pageNo, pageSize);//设置分页
        List<CouponDetailEntity> couponDetailEntityList = couponDetailMapper.query(couponDetailEntity);//查询entityList结果集
        try {
            Map<String, SysDictVo> couponTypeMap = sysDictService.queryDictMapByCode(SysDictConstant.COUPON_TYPE);
            Map<String, SysDictVo> useStatusMap = sysDictService.queryDictMapByCode(SysDictConstant.COUPON_USE_STATUS);
            PageInfo<CouponDetailEntity> entityPage = new PageInfo<>(couponDetailEntityList);//以entityList结果创建entityPage
            PageInfo<CouponDetailVo> page = FitBeanCopyUtil.pageInfoCopy(entityPage, CouponDetailVo.class);//复制entityPage到page
            for (CouponDetailVo vo : page.getList()) {//遍历page设置类型，状态
                vo.setCouponTypeName(couponTypeMap.get(vo.getCouponType().toString()).getName());
                vo.setStatusName(useStatusMap.get(vo.getStatus()).getName());
            }
            return page;
        } catch (Exception e) {
            logger.error("voList copy to entityList error", e);
        }
        return null;
    }

    /**
     * 编辑修改优惠券
     * @param couponVo
     * @throws CommonBizException
     */
    @Override
    public void editCoupon(CouponVo couponVo) throws CommonBizException {
        logger.info("修改优惠券：请求参数{}", new Gson().toJson(couponVo));
        CouponEntity couponEntity = new CouponEntity();
        BeanUtil.copyProperties(couponVo, couponEntity);//参数vo复制到entity进行数据库修改操作
        int result = couponMapper.update(couponEntity);
        if (result != 1) {
            logger.info("优惠券修改失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_EDIT_ERROR);
        }
    }

    /**
     * 下架优惠券
     *
     * @param couponId
     */
    @Override
    public void shelvesCoupon(int couponId) throws CommonBizException {
        logger.info("下架优惠券：优惠券id：{}", couponId);
        CouponEntity couponEntity = couponMapper.selectById(couponId);
        if (couponEntity.getStatus() == FitConstant.COUPON_STATUS_OFFLINE) {
            logger.info("该优惠券已下架！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_OFFLINE_ERROR);
        }
        couponEntity.setStatus(FitConstant.COUPON_STATUS_OFFLINE);//设置优惠券状态2，下线
        int result = couponMapper.update(couponEntity);//更新数据库
        if (result != 1) {
            logger.info("优惠券修改失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_EDIT_ERROR);
        }
    }

    /**
     * 查询优惠券详情
     *
     * @param couponVo
     * @return
     */
    @Override
    public CouponVo queryById(CouponVo couponVo) {
        logger.info("查询优惠券详情：请求参数{}",new Gson().toJson(couponVo));
        Map<String, SysDictVo> map = sysDictService.queryDictMapByCode(SysDictConstant.PRODUCT_TYPE);
        Map<String, SysDictVo> couponTypeMap = sysDictService.queryDictMapByCode(SysDictConstant.COUPON_TYPE);
        Map<String, SysDictVo> issueChannelMap = sysDictService.queryDictMapByCode(SysDictConstant.COUPON_ISSUE_CHANNEL);
        CouponEntity couponEntity = couponMapper.selectById(couponVo.getId());//以vo.id查询couponEntity
        BeanUtil.copyProperties(couponEntity, couponVo); //复制entity到vo
        couponVo.setAimName(sysDictService.queryDictNameByGsonValue(couponVo.getAim(), map));
        couponVo.setCouponTypeName(couponTypeMap.get(couponVo.getCouponType().toString()).getName());
        couponVo.setGymTypeName(FitConstant.GTM_TYPE_ALL_NAME);
        if (couponVo.getIssueChannel() != null) {
            logger.info("设置发放渠道成功");
            couponVo.setIssueChannelName(issueChannelMap.get(couponVo.getIssueChannel().toString()).getName());
        }
        return couponVo;
    }

    /**
     * 领取课程体验券
     * 如果是邀请者的话，在被邀请者结束第一次课程后，获取体验券
     * 如果是被邀请者，会获取一张体验券和一张新人券
     *
     * @param userId
     * @param identityAttribute
     */
    @Override
    public void receiveCourseCoupon(int userId, int identityAttribute) throws CommonBizException {
        logger.info("领取课程体验券：用户id：{},身份属性：{}",userId,identityAttribute);
        //被邀请人赠送新人券
        if (FitConstant.COUPON_IDENTITYATTRIBUTE_INVITEE == identityAttribute) {//身份属性2，被邀请者
            logger.info("身份属性：被邀请者（新人）");
            newUserReceiveCoupon(userId);
        }
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponType(FitConstant.COUPON_TYPE_COURSE_EXPERIENCE);//优惠券类型3
        List<CouponEntity> couponEntityList = couponMapper.query(couponEntity);//以entity查询返回List
        if (CollUtil.isEmpty(couponEntityList)) {//entityList为空，设置
            couponEntity.setCreateTime(new Date());
            couponEntity.setOperateName(FitConstant.SYS_USER_NAME);
            couponEntity.setOperateId(FitConstant.SYS_USER_ID);
            couponEntity.setName("老带新");
            couponEntity.setDescription("老带新，发放课程体验券!");
            String[] aimArr = {FitConstant.AIM_GROUP_EXERCISES, FitConstant.AIM_GROUPUSCULE};
            couponEntity.setAim(new Gson().toJson(aimArr));
            couponEntity.setUseNum(0);
            couponEntity.setReceiveNum(0);
            couponEntity.setCreateTime(new Date());
            couponEntity.setIssueNum(0);
            couponEntity.setGymType(FitConstant.GTM_TYPE_ALL);
            couponEntity.setLimitNum(0);
            couponEntity.setStartTime(new Date());
            couponEntity.setUpdateTime(new Date());
            couponEntity.setIssueChannel(FitConstant.ISSUE_CHANNEL_SYS);
            couponEntity.setUserType(FitConstant.USER_ALL);
            couponEntity.setValidDays(FitConstant.COUPON_TYPE_COURSE_VALID_DAY);
            couponEntity.setPrice(FitConstant.COUPON_DEDUCTIBLE_PRICE);
            couponEntity.setValidType(FitConstant.VALID_TYPE_RECEIVE);
            couponEntity.setStatus(FitConstant.COUPON_STATUS_ONLINE);
            couponEntity.setVersion(1);
            couponMapper.save(couponEntity);
        } else {//有记录
            couponEntity = couponEntityList.get(0);
            if (couponEntity.getStatus() == FitConstant.COUPON_STATUS_OFFLINE) {//判断优惠券是否下线
                logger.info("该优惠券已下线！");
                throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_OFFLINE_ERROR);
            }
        }
        int couponCount = couponDetailMapper.queryCountByUserIdAndType(userId, couponEntity.getCouponType(), identityAttribute);
        if (FitConstant.COUPON_IDENTITYATTRIBUTE_INVITER == identityAttribute) {//身份属性1，老用户
            logger.info("身份属性：邀请者（老用户）");
            int inviteUserCount = couponDetailMapper.queryCountByInviteUserId(userId);
            if (couponCount >= inviteUserCount) {
                logger.info("该用户已领取优惠券，不能重复领取！");
                throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_DETAIL_EXISTS_ERROR);
            }
        } else {
            if (couponCount != 0) {
                logger.info("该用户已领取优惠券，不能重复领取！");
                throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_DETAIL_EXISTS_ERROR);
            }
        }
        saveCouponDetailEntity(couponEntity, userId, identityAttribute);
    }

    /**
     * 代金券领取
     *
     * @param userId
     * @param couponId
     */
    @Override
    public void receiveSubstituteMoneyCoupon(int userId, int couponId) throws CommonBizException {
        logger.info("领取代金券：用户id：{},优惠券id：{}",userId,couponId);
        CouponEntity couponEntity = couponMapper.selectById(couponId);
        if (couponEntity == null) {
            logger.info("优惠券不存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_NOT_EXISTS_ERROR);
        } else {
            if (couponEntity.getStatus() == FitConstant.COUPON_STATUS_OFFLINE) {
                logger.info("该优惠券已下架！");
                throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_OFFLINE_ERROR);
            } else {
                if (couponEntity.getReceiveNum() >= couponEntity.getIssueNum()) { //领取数量大于发放数量，无法发放
                    logger.info("优惠券被抢光了！");
                    throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_OUT_COUNT);
                } else {
                    int userReceiveCount = couponDetailMapper.queryCountByUserIdAndCouponId(userId, couponId);
                    if (userReceiveCount >= couponEntity.getLimitNum()) {//用户领取数量大于领取数量限制
                        logger.info("优惠券领取数量达到上限！");
                        throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_RECEIVE_OUT);
                    }
                    saveCouponDetailEntity(couponEntity, userId, null);
                    couponEntity.setReceiveNum(couponEntity.getReceiveNum() + 1); //更新，领取数量+1
                    int result = couponMapper.update(couponEntity);
                    if (result != 1) {
                        logger.info("优惠券修改失败！");
                        throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_EDIT_ERROR);
                    }
                }
            }
        }
    }

    /**
     * 新人注册优惠券
     *
     * @param userId
     */
    @Override
    public void newUserReceiveCoupon(int userId) throws CommonBizException {
        logger.info("新人注册优惠券：用户id：{}",userId);
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponType(FitConstant.COUPON_TYPE_NEW_USER_EXPERIENCE);//设置优惠券类型2，新人体验券
        List<CouponEntity> couponEntityList = couponMapper.query(couponEntity);
        if (CollUtil.isEmpty(couponEntityList)) {//查询结果空，新人注册
            couponEntity.setCreateTime(new Date());
            couponEntity.setOperateName(FitConstant.SYS_USER_NAME);
            couponEntity.setOperateId(FitConstant.SYS_USER_ID);
            couponEntity.setName("新人注册");
            couponEntity.setDescription("新人注册，自动发放!");
            String[] aimArr = {FitConstant.AIM_GROUP_EXERCISES, FitConstant.AIM_GROUPUSCULE};//aim适用类型
            couponEntity.setAim(new Gson().toJson(aimArr));
            couponEntity.setPrice(0D);
            couponEntity.setCreateTime(new Date());
            couponEntity.setGymType(FitConstant.GTM_TYPE_ALL);
            couponEntity.setIssueNum(0);
            couponEntity.setUseNum(0);
            couponEntity.setReceiveNum(0);
            couponEntity.setLimitNum(FitConstant.COUPON_LIMIT_NUM);
            couponEntity.setStartTime(new Date());
            couponEntity.setUpdateTime(new Date());
            couponEntity.setUserType(FitConstant.USER_NEW);
            couponEntity.setIssueChannel(FitConstant.ISSUE_CHANNEL_SYS);
            couponEntity.setValidDays(FitConstant.COUPON_TYPE_NEW_USER_VALID_DAY);
            couponEntity.setValidType(FitConstant.VALID_TYPE_RECEIVE);
            couponEntity.setStatus(FitConstant.COUPON_STATUS_ONLINE);
            couponEntity.setVersion(1);
            couponMapper.save(couponEntity);//保存数据
        } else {
            couponEntity = couponEntityList.get(0); //若有数据则取出第一条
            if (couponEntity.getStatus() == FitConstant.COUPON_STATUS_OFFLINE) {//优惠券已下线抛异常
                logger.info("该优惠券已下架！");
                throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_OFFLINE_ERROR);
            }
        }
        int couponCount = couponDetailMapper.queryCountByUserIdAndType(userId, couponEntity.getCouponType(), null);
        if (couponCount == 0) {
            for (int i = 0; i < (couponEntity.getLimitNum() - couponCount); i++) {
                saveCouponDetailEntity(couponEntity, userId, null);
            }
        } else {
            logger.info("该用户已经是老用户了！");
            throw new CommonBizException(ExpCodeEnum.MARKET_USER_EXISTS_ERROR);
        }
    }

    /**
     * 保存优惠券详情
     *
     * @param couponEntity
     * @param userId
     */
    private void saveCouponDetailEntity(CouponEntity couponEntity, int userId, Integer identityAttribute) throws CommonBizException {
        logger.info("保存优惠券详情：请求参数：{},用户id：{},身份属性：{}",new Gson().toJson(couponEntity),userId,identityAttribute);
        CouponDetailEntity couponDetailEntity = new CouponDetailEntity();
        couponDetailEntity.setCode(idGenerator.nextStringId());
        couponDetailEntity.setCouponId(couponEntity.getId());
        couponDetailEntity.setCreateTime(new Date());
        couponDetailEntity.setStartTime(new Date());
        //DateUtil.offset三个参数（Date起始日期，改变DateField.DAY_OF_MONTH改变的日期域，改变天数）返回date型；
        couponDetailEntity.setEndTime(DateUtil.offset(couponDetailEntity.getStartTime(), DateField.DAY_OF_MONTH, couponEntity.getValidDays()));
        couponDetailEntity.setStatus(FitConstant.COUPON_DETAIL_STATUS_INIT);
        couponDetailEntity.setUserId(userId);
        couponDetailEntity.setVersion(1);
        couponDetailEntity.setIdentityAttribute(identityAttribute);
        int result = couponDetailMapper.save(couponDetailEntity);
        if (result != 1) {
            logger.info("优惠券保存失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_DETAIL_SAVE_ERROR);
        }
    }


    /**
     * 使用优惠券
     *
     * @param userId         用户id
     * @param couponDetailId 优惠券明细id
     * @param gymId          场馆id
     * @param courseProductManageId   排期id
     * @param orderNo        订单编号
     * @throws CommonBizException
     */
    @Override
    public void useCouponDetail(Integer userId, Integer couponDetailId, Integer gymId, Integer courseProductManageId, String orderNo) throws CommonBizException {
        logger.info("使用优惠券：用户id：{},优惠券明细id：{},场馆id：{},排期id：{},订单编号：{}",userId,couponDetailId,gymId,courseProductManageId,orderNo);
        CouponDetailEntity couponDetailEntity = couponDetailMapper.selectById(couponDetailId);
        if (couponDetailEntity == null) {
            logger.info("该优惠券不存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_NOT_EXISTS_ERROR);
        }
        if (!userId.equals(couponDetailEntity.getUserId())) {
            logger.info("该优惠券不属于该用户！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_NOT_BELONG_USER);
        }
        if (!couponDetailEntity.getStatus().equals(FitConstant.COUPON_DETAIL_STATUS_INIT)) {
            logger.info("该优惠券已使用！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_USED);
        }
        if (couponDetailEntity.getStartTime().after(new Date())) {
            logger.info("该优惠券未到使用时间！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_DOT_USE);
        }
        if (couponDetailEntity.getEndTime().before(new Date())) {
            logger.info("该优惠券已过使用时间！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_TIMEOUT);
        }
        String gymName = couponDetailMapper.queryGymNameById(gymId);//根据gymId查到gymName
        Integer orderId = couponDetailMapper.queryOrderIdByNo(orderNo);//根据orderNo查到orderId
        CourseInfoEntity courseInfoEntity = couponDetailMapper.queryCourseInfoByCourseProductManageId(courseProductManageId);//根据排期id查到课程信息entity
        CouponEntity couponEntity = couponMapper.selectById(couponDetailEntity.getCouponId());//根据couponId查到couponEntity
        //订单不存在
        if (orderId == null) {
            logger.info("订单号不存在！");
            throw new CommonBizException(ExpCodeEnum.ORDER_NOT_EXISTS);
        }
        //发放数量有限并且使用数量超出发放数量
        if (couponEntity.getIssueNum() != 0 && couponEntity.getUseNum() >= couponEntity.getIssueNum()) {
            logger.info("优惠券使用数量超出限额！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_USED_OUT);
        }
        //课程信息不存在
        if (courseInfoEntity == null) {
            logger.info("该课程不存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COURSE_NOT_EXISTS_ERROR);
        }
        boolean courseTypeFlag = false;
        List<String> courseTypeList = new Gson().fromJson(couponEntity.getAim(), new TypeToken<List<String>>() {
        }.getType());
        for (String type : courseTypeList) {
            //课程适用当前类型优惠券，flag=true
            if (courseInfoEntity.getCourseType().toString().equals(type)) {
                courseTypeFlag = true;
                break;
            }
        }
        //该优惠券不适用于该课程，抛异常
        if (!courseTypeFlag) {
            logger.info("该优惠券不适用于该课程！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COURSE_USE_COUPON_ERROR);
        }
        //设置couponDetialEntity的属性，并update到数据库
        couponDetailEntity.setStatus(FitConstant.COUPON_DETAIL_STATUS_USED);
        couponDetailEntity.setGymId(gymId);
        couponDetailEntity.setGymName(gymName);
        couponDetailEntity.setOrderId(orderId);
        couponDetailEntity.setOrderNo(orderNo);
        couponDetailEntity.setProductId(courseProductManageId);
        couponDetailEntity.setProductName(courseInfoEntity.getCourseName());
        couponDetailMapper.update(couponDetailEntity);
        couponEntity.setUseNum(couponEntity.getUseNum() + 1);//使用次数+1
        couponMapper.update(couponEntity);
    }

    /**
     * h5页面查询该用户的优惠券列表
     *
     * @param userId
     * @param couponId
     * @param isNewUser
     * @return
     * @throws CommonBizException
     */
    @Override
    public List<CouponH5Vo> selectH5CouponList(Integer userId, Integer couponId, boolean isNewUser) throws CommonBizException {
        logger.info("h5页面查询该用户的优惠券列表：用户id：{},优惠券id：{},是否为新用户：{}",userId,couponId,isNewUser);
        CouponDetailEntity couponDetailEntity = new CouponDetailEntity();
        couponDetailEntity.setUserId(userId);
        couponDetailEntity.setCouponId(couponId);
        List<CouponDetailEntity> couponDetailEntityList = couponDetailMapper.query(couponDetailEntity);
        if (couponDetailEntityList == null) {//查询结果为空，优惠券领取失败
            logger.info("优惠券领取失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_RECEIVE_ERROR);
        }
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(couponId);
        if (isNewUser) {//新用户
            couponEntity.setNewUser(isNewUser);
            couponEntity.setCouponType(FitConstant.COUPON_TYPE_NEW_USER_EXPERIENCE);//优惠券类型：新人体验券
        }
        //查询该用户userId的优惠券列表couponEntityList
        List<CouponEntity> couponEntityList = couponMapper.selectCouponOrNewUserByCouponId(couponEntity);
        Map<String, SysDictVo> map = sysDictService.queryDictMapByCode(SysDictConstant.PRODUCT_TYPE);
        List<CouponH5Vo> couponH5VoList = new ArrayList<>();
        //遍历couponEntityList并存到couponH5VoList
        for (CouponEntity entity : couponEntityList) {
            CouponH5Vo couponH5Vo = new CouponH5Vo();
            couponH5Vo.setAimName(sysDictService.queryDictNameByGsonValue(entity.getAim(), map));
            couponH5Vo.setId(entity.getId());
            couponH5Vo.setCouponName(entity.getName());
            couponH5Vo.setPrice(entity.getPrice());
            couponH5Vo.setValidDays(entity.getValidDays());
            couponH5VoList.add(couponH5Vo);
        }
        return couponH5VoList;
    }

    /**
     * 将过期优惠券的状态修改为失效
     */
    @Override
    public Integer editFailureCouponStatus(){
        return couponDetailMapper.editFailureCouponStatus(FitConstant.COUPON_DETAIL_STATUS_FAILURE);//3过期，返回过期状态位3
    }

}