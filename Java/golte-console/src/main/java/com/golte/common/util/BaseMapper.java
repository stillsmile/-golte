package com.golte.common.util;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.RowBoundsMapper;
import tk.mybatis.mapper.common.SqlServerMapper;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

/**
 * @author GXS
 * @date 2018年4月9日
 * @Description:mapper基类
 */
public interface BaseMapper<T> extends SqlServerMapper<T>, BaseSelectMapper<T>, BaseUpdateMapper<T>, BaseDeleteMapper<T>, ExampleMapper<T>, RowBoundsMapper<T>, Marker {

}
