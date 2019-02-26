package com.comma.fit.fitcrm.market.mapper;

import java.util.List;
import com.comma.fit.fitcrm.market.entity.MarketChannelEntity;

/**
 * @author comma
 */
public interface IMarketChannelMapper{
	
	public List<MarketChannelEntity> query(MarketChannelEntity marketChannel);
	public int save(MarketChannelEntity marketChannel);
	public MarketChannelEntity selectById(Integer id);

	public int update(MarketChannelEntity marketChannel);
	
	public int delete(MarketChannelEntity marketChannel);
}
