package com.comma.fit.fitcrm.common.mapper;

import java.util.List;
import com.comma.fit.fitcrm.common.entity.SysDictTypeEntity;
/**
 * @author comma
 */
public interface ISysDictTypeMapper{
	
	public List<SysDictTypeEntity> query(SysDictTypeEntity sysDictType);
	public int save(SysDictTypeEntity sysDictType);
	public SysDictTypeEntity selectById(Integer id);

	public int update(SysDictTypeEntity sysDictType);
	
	public int delete(SysDictTypeEntity sysDictType);
}
