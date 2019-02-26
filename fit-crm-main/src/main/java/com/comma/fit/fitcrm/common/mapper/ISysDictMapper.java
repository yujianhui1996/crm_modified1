package com.comma.fit.fitcrm.common.mapper;

import java.util.List;
import com.comma.fit.fitcrm.common.entity.SysDictEntity;
/**
 * @author comma
 */
public interface ISysDictMapper{
	
	public List<SysDictEntity> query(SysDictEntity sysDict);
	public int save(SysDictEntity sysDict);
	public SysDictEntity selectById(Integer id);

	public int update(SysDictEntity sysDict);
	
	public int delete(SysDictEntity sysDict);
}
