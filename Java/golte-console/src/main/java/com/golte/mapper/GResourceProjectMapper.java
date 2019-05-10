package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GResourceProject;
import com.golte.resource.service.data.GetPointExportInData;
import com.golte.resource.service.data.GetProjectInData;
import com.golte.resource.service.data.GetProjectOutData;
import com.golte.system.service.data.GetResourceProjectInData;
import com.golte.system.service.data.GetResourceProjectOutData;

import java.util.List;

public interface GResourceProjectMapper extends BaseMapper<GResourceProject> {
	
	/**
	 * @Description: 查询资源项目列表
	 * @author Ws
	 * @date 2019/4/2
	 */
	List<GetProjectOutData> selectProjectListByCondition(GetProjectInData inData);

	/**
	 * @Description: 查询项目列表
	 * @author wyf
	 * @date 2019/4/1
	 */
	List<GetResourceProjectOutData> selectListByCity(GetResourceProjectInData inData);

	/**
	 * @Description: 导入查询已存在的项目
	 * @author ws
	 * @date 2019/4/8
	 */
	GResourceProject selectExistProjectList(GetPointExportInData inData);

	/**
	 * @Description: 查询项目关联的合同数量
	 * @author ws
	 * @date 2019/4/8
	 */
	List<GetProjectOutData> selectExistContract(Long id);
}