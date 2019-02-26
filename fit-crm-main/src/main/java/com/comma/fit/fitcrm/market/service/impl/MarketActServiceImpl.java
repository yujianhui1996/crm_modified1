package com.comma.fit.fitcrm.market.service.impl;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.comma.fit.fitcrm.common.constant.CommonConstant;
import com.comma.fit.fitcrm.common.constant.FitConstant;
import com.comma.fit.fitcrm.common.constant.SysDictConstant;
import com.comma.fit.fitcrm.common.service.ISysDictService;
import com.comma.fit.fitcrm.common.vo.SysDictVo;
import com.comma.fit.fitcrm.exception.CommonBizException;
import com.comma.fit.fitcrm.exception.ExpCodeEnum;
import com.comma.fit.fitcrm.market.entity.*;
import com.comma.fit.fitcrm.market.mapper.ICouponMapper;
import com.comma.fit.fitcrm.market.mapper.IMarketActTemplateMapper;
import com.comma.fit.fitcrm.market.mapper.IMarketChannelMapper;
import com.comma.fit.fitcrm.market.vo.*;
import com.comma.fit.fitcrm.market.mapper.IMarketActMapper;
import com.comma.fit.fitcrm.market.service.IMarketActService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.comma.fit.fitcrm.util.FitBeanCopyUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author magang
 */
@org.springframework.stereotype.Service
@Service(version = CommonConstant.VERSION)
@Transactional(rollbackFor = CommonBizException.class)
public class MarketActServiceImpl implements IMarketActService {
    private static final Logger logger = LoggerFactory.getLogger(MarketActServiceImpl.class);
    @Autowired
    private IMarketActMapper marketActMapper;
    @Autowired
    private IMarketChannelMapper marketChannelMapper;
    @Autowired
    private IMarketActTemplateMapper marketActTemplateMapper;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private ICouponMapper couponMapper;

    /**
     * 保存活动渠道
     *
     * @param marketChannelVo
     */
    @Override
    public void saveMarketChannel(MarketChannelVo marketChannelVo) throws CommonBizException {
        logger.info("保存活动渠道：请求参数：{}",new Gson().toJson(marketChannelVo));
        MarketChannelEntity query = new MarketChannelEntity();
        query.setChannelName(marketChannelVo.getChannelName());
        //把参数存到query对象查询数据库中是否已存在该渠道
        List<MarketChannelEntity> marketChannelList = marketChannelMapper.query(query);
        //结果集合不为空，渠道已存在
        if (CollUtil.isNotEmpty(marketChannelList)) {
            logger.info("活动渠道已存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_ACT_CHANNEL_EXISTS);
        }
        MarketChannelEntity marketChannelEntity = new MarketChannelEntity();
        //把vo拷贝到entity对象
        BeanUtil.copyProperties(marketChannelVo, marketChannelEntity);
        //设置默认渠道等级1，创建时间
        marketChannelEntity.setChannelLevel(FitConstant.MARKET_CHANNEL_LEVEL);
        marketChannelEntity.setCreateTime(new Date());
        //保存到数据库
        int result = marketChannelMapper.save(marketChannelEntity);
        if (result != 1) {
            logger.info("活动渠道保存失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_ACT_CHANNEL_SAVE_ERROR);
        }
    }

    /**
     * 保存活动行为
     * @param marketActVo
     * @throws CommonBizException
     */
    @Override
    public void saveMarketAct(MarketActVo marketActVo) throws CommonBizException {
        logger.info("保存活动行为：请求参数：{}",new Gson().toJson(marketActVo));
        MarketActEntity marketActEntity = new MarketActEntity();
        BeanUtil.copyProperties(marketActVo, marketActEntity);
        //以marketActVo.couponId查是否存在该coupon
        CouponEntity couponEntity=couponMapper.selectById(marketActVo.getCouponId());
        if(couponEntity==null){
            logger.info("优惠券不存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_COUPON_NOT_EXISTS_ERROR);
        }
        marketActEntity.setActUserType(marketActVo.getActUserType());
        marketActEntity.setVersion(1);
        marketActEntity.setOrderCount(0);
        marketActEntity.setRegisteredCount(0);
        marketActEntity.setUvCount(0);
        marketActEntity.setCreateTime(new Date());
        marketActEntity.setUpdateTime(new Date());
        marketActEntity.setStatus(FitConstant.MARKET_STATUS_ONLINE);
        //设置活动行为属性，并保存活动行为
        int result = marketActMapper.save(marketActEntity);
        if (result != 1) {
            logger.info("活动行为保存失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_ACT_SAVE_ERROR);
        }
    }

    /**
     * 获取渠道列表
     *
     * @return
     */
    @Override
    public List<MarketChannelVo> queryMarketChannel() {
        logger.info("获取渠道列表");
        List<MarketChannelEntity> marketChannelEntityList = marketChannelMapper.query(new MarketChannelEntity());
        List<MarketChannelVo> marketChannelVoList = null;
        try {
            marketChannelVoList = FitBeanCopyUtil.listBeanCopy(marketChannelEntityList, MarketChannelVo.class);
        } catch (Exception e) {
            logger.error("entityList copy to voList error", e);
        }
        return marketChannelVoList;
    }

    /**
     * 获取活动模板列表
     *
     * @return
     */
    @Override
    public List<MarketActTemplateVo> queryMarketActTemplate() {
        logger.info("获取活动模板列表");
        List<MarketActTemplateEntity> marketActTemplateEntityList = marketActTemplateMapper.query(new MarketActTemplateEntity());
        List<MarketActTemplateVo> marketActTemplateVoList = null;
        try {
            marketActTemplateVoList = FitBeanCopyUtil.listBeanCopy(marketActTemplateEntityList, MarketActTemplateVo.class);
        } catch (Exception e) {
            logger.error("entityList copy to voList error", e);
        }
        return marketActTemplateVoList;
    }

    @Override
    public PageInfo<MarketActVo> query(MarketActVo marketActVo,int pageNo,int pageSize) {
        logger.info("查询活动行为页面信息：请求参数：{},页面编号：{},页面大小：{}",new Gson().toJson(marketActVo),pageNo,pageSize);
        MarketActEntity marketActEntity = new MarketActEntity();
        //vo复制到entity
        BeanUtil.copyProperties(marketActVo, marketActEntity);
        //设置分页
        PageHelper.startPage(pageNo, pageSize);
        //以marketActEntity查询entityList结果集
        List<MarketActEntity> marketActEntityList = marketActMapper.query(marketActEntity);
        try {
            PageInfo<MarketActEntity> marketActEntityPageInfo=new PageInfo<>(marketActEntityList);
            PageInfo<MarketActVo> page = FitBeanCopyUtil.pageInfoCopy(marketActEntityPageInfo, MarketActVo.class);
            for (MarketActVo actVo : page.getList()) {
                MarketChannelVo marketChannelVo=new MarketChannelVo();
                marketChannelVo.setChannelName(actVo.getChannelName());
                actVo.setMarketChannelVo(marketChannelVo);
                MarketActTemplateVo marketActTemplateVo=new MarketActTemplateVo();
                marketActTemplateVo.setUri(actVo.getTemplateUri()+"?id="+actVo.getId());
                actVo.setMarketActTemplateVo(marketActTemplateVo);
            }
            return page;
        } catch (Exception e) {
            logger.error("voList copy to entityList error", e);
        }
        return null;
    }

    /**
     * 修改活动
     *
     * @param marketActVo
     * @throws CommonBizException
     */
    @Override
    public void editMarketAct(MarketActVo marketActVo) throws CommonBizException {
        logger.info("修改活动：请求参数：{}",new Gson().toJson(marketActVo));
        MarketActEntity marketActEntity = new MarketActEntity();
        BeanUtil.copyProperties(marketActVo, marketActEntity);
        int result = marketActMapper.update(marketActEntity);
        if (result != 1) {
            logger.info("活动修改失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_ACT_EDIT_ERROR);
        }
    }

    /**
     * 更新活动状态
     *
     * @param marketActVo
     * @throws CommonBizException
     */
    @Override
    public void editMarketActStatus(MarketActVo marketActVo) throws CommonBizException {
        logger.info("更新活动状态：请求参数：{}",new Gson().toJson(marketActVo));
        //检验该活动是否存在
        MarketActEntity marketActEntity = marketActMapper.selectById(marketActVo.getId());
        if (marketActEntity==null){
            logger.info("活动不存在！");
            throw new CommonBizException(ExpCodeEnum.MARKET_ACT_NOT_EXISTS_ERROR);
        }
        if (!marketActVo.getVersion().equals(marketActEntity.getVersion())) {
            logger.info("请勿重复提交！");
            throw new CommonBizException(ExpCodeEnum.NO_REPEAT);
        }
        //活动状态位上线，则变为下线
        if (marketActEntity.getStatus().equals(FitConstant.MARKET_STATUS_ONLINE)) {
            marketActEntity.setStatus(FitConstant.MARKET_STATUS_OFFLINE);
        } else {
            marketActEntity.setStatus(FitConstant.MARKET_STATUS_ONLINE);
        }
        int result = marketActMapper.update(marketActEntity);
        if (result != 1) {
            logger.info("活动修改失败！");
            throw new CommonBizException(ExpCodeEnum.MARKET_ACT_EDIT_ERROR);
        }
    }

    /**
     * 按marketActVo.id查找
     * @param marketActVo
     * @return
     */
    @Override
    public MarketActVo queryById(MarketActVo marketActVo) {
        logger.info("按id查找活动行为：请求参数：{}",new Gson().toJson(marketActVo));
        //查找该活动id的活动对象，并复制给vo
        MarketActEntity marketActEntity = marketActMapper.selectById(marketActVo.getId());
        BeanUtil.copyProperties(marketActEntity, marketActVo);

        Map<String, SysDictVo> map = sysDictService.queryDictMapByCode(SysDictConstant.ACT_USER_TYPE);
        if (StrUtil.isNotEmpty(marketActVo.getActUserType())){
            marketActVo.setActUserType(map.get(marketActVo.getActUserType()).getName());
        }

        MarketActTemplateVo marketActTemplateVo=new MarketActTemplateVo();
        marketActTemplateVo.setTemplateName(marketActVo.getTemplateName());
        marketActVo.setMarketActTemplateVo(marketActTemplateVo);
        MarketChannelVo marketChannelVo=new MarketChannelVo();
        marketChannelVo.setChannelName(marketActVo.getChannelName());
        marketActVo.setMarketChannelVo(marketChannelVo);
        return marketActVo;
    }

    /**
     * 获取活动模板详情
     *
     * @param marketActId
     * @return
     */
    @Override
    public MarketActExtVo getMarketActExt(Integer marketActId) {
        logger.info("获取活动模板详情：活动行为id：{}",marketActId);
        MarketActExtEntity marketActExtEntity = marketActMapper.selectMarketActExtById(marketActId);
        //复制ActExtEntity到ActExtVo
        MarketActExtVo marketActExtVo = new MarketActExtVo();
        BeanUtil.copyProperties(marketActExtEntity, marketActExtVo);
        //生成唯一不重复的时间戳
        marketActExtVo.setTimeStamp(UUID.randomUUID().toString());
        Map<String, SysDictVo> map = sysDictService.queryDictMapByCode(SysDictConstant.PRODUCT_TYPE);
        marketActExtVo.setAimName(sysDictService.queryDictNameByGsonValue(marketActExtEntity.getAimId(), map));
        return marketActExtVo;
    }

}