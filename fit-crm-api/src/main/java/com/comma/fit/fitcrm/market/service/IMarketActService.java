package com.comma.fit.fitcrm.market.service;

import com.comma.fit.fitcrm.market.vo.MarketActExtVo;
import com.comma.fit.fitcrm.market.vo.MarketActTemplateVo;
import com.comma.fit.fitcrm.market.vo.MarketChannelVo;
import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.market.vo.MarketActVo;
import com.comma.fit.fitcrm.exception.CommonBizException;

import java.util.List;

public interface IMarketActService{
    List<MarketChannelVo> queryMarketChannel();

    List<MarketActTemplateVo> queryMarketActTemplate();

    public PageInfo<MarketActVo> query(MarketActVo marketActVo,int pageNo,int pageSize);

    void saveMarketChannel(MarketChannelVo marketChannelVo) throws CommonBizException;

    public void saveMarketAct(MarketActVo marketActVo) throws CommonBizException;

	public void editMarketAct(MarketActVo marketActVo) throws CommonBizException;

    void editMarketActStatus(MarketActVo marketActVo) throws CommonBizException;

    MarketActVo queryById(MarketActVo marketActVo);

    MarketActExtVo getMarketActExt(Integer marketActId);
}
