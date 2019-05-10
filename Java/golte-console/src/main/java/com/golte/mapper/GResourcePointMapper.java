package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GResourcePoint;
import com.golte.resource.service.data.GetPointInData;
import com.golte.resource.service.data.GetPointOutData;
import com.golte.resource.service.data.GetPointTableOutData;
import com.golte.resource.service.data.GetPointTreeOutData;
import com.golte.system.service.data.GetResourcePointInData;
import com.golte.system.service.data.GetResourcePointOutData;

import java.util.List;

public interface GResourcePointMapper extends BaseMapper<GResourcePoint> {


	/**
	 * @param inData
	 * @return
	 */
	List<GetResourcePointOutData> selectPointByParentId(GetResourcePointInData inData);


	/**
	 * @des 根据三级id查询一二三级id详情
	 *@author  Ws
	 * @data 2019/4/3
	 */
	List<GetPointOutData> selectPointInfoByid(GetPointInData inData);

	/**
	 * @des 查询资源点位Tree
	 *@author  Ws
	 * @data 2019/4/4
	 */
	List<GetPointTreeOutData> selectPointTreeList();

	/**
	 * @des 查询资源点位Table
	 *@author  Ws
	 * @data 2019/4/4
	 */
	List<GetPointTableOutData> selectPointTableList(GetPointInData inData);

}