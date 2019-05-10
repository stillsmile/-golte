package com.golte.mapper;


import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GResourceProjectDetail;
import com.golte.resource.service.data.GetProjectDetailInData;
import com.golte.resource.service.data.GetProjectDetailOutData;
import com.golte.system.service.data.GetMainInData;
import com.golte.system.service.data.GetMainTopOutData;

import java.util.List;

public interface GResourceProjectDetailMapper extends BaseMapper<GResourceProjectDetail> {

	/**
	 * @Description: 查询资源项目详细列表
	 * @author Ws
	 * @date 2019/4/3
	 */
	List<GetProjectDetailOutData> selectProjectDetailListByCondition(GetProjectDetailInData inData);

	/**
	 * @Description: 查询资源排行
	 * @author Ws
	 * @date 2019/4/3
	 */
	List<GetMainTopOutData> selectResourceTypeList(GetMainInData inData);

	/**
	 * @Description: 查询资源排行详细
	 * @author Ws
	 * @date 2019/4/3
	 */
	List<GetMainTopOutData> selectResourceTypeListDetail(GetMainInData inData);

	/**
	 * @Description: 查询资源被使用的数量
	 * @author Ws
	 * @date 2019/4/17
	 */
	List<GetProjectDetailOutData> selectExitsNum(Long id);

}