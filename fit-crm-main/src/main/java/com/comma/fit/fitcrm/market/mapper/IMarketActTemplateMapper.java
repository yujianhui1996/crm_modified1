package com.comma.fit.fitcrm.market.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.comma.fit.fitcrm.market.entity.MarketActTemplateEntity;

public interface IMarketActTemplateMapper{
	
	public List<MarketActTemplateEntity> query(MarketActTemplateEntity marketActTemplate);
	public int save(MarketActTemplateEntity marketActTemplate);
	public MarketActTemplateEntity selectById(Integer id);

	public int update(MarketActTemplateEntity marketActTemplate);
	
	public int delete(MarketActTemplateEntity marketActTemplate);
}
