package com.comma.fit.fitcrm.market.mapper;

import java.util.List;
import java.util.Map;

import com.comma.fit.fitcrm.market.entity.MarketActExtEntity;
import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.market.entity.MarketActEntity;

public interface IMarketActMapper {

    public List<MarketActEntity> query(MarketActEntity marketAct);

    public int save(MarketActEntity marketAct);

    public MarketActEntity selectById(Integer id);

    public MarketActExtEntity selectMarketActExtById(Integer id);

    public int update(MarketActEntity marketAct);

    public int delete(MarketActEntity marketAct);
}
