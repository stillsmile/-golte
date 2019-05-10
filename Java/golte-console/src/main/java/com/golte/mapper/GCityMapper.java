package com.golte.mapper;

import com.golte.basicdata.service.data.GetCityInData;
import com.golte.basicdata.service.data.GetCityOutData;
import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GCity;
import com.golte.system.service.data.GetMainInData;
import com.golte.system.service.data.GetMainOutData;

import java.util.List;

public interface GCityMapper extends BaseMapper<GCity> {
    /**
     * @Description: 年度指标信息查询
     * @author ws
     * @date 2019/4/12
     */
    GetMainOutData selectYearTargetByCondition(GetMainInData inData);

    /**
     * @Description: 查询录入人员城市列表
     * @author wyf
     * @date 2019/3/12
     */
    List<GCity> selectByEnterPerson(Long empId);

    /**
     * @Description: 查询部门负责人城市列表
     * @author wyf
     * @date 2019/3/12
     */
    List<GCity> selectByBusinessPrincipal(Long empId);

    /**
     * @Description: 查询城市公司负责人城市列表
     * @author wyf
     * @date 2019/3/12
     */
    List<GCity> selectByCityPrincipal(Long empId);

    /**
     * @Description: 查询中心城市负责人城市列表
     * @author wyf
     * @date 2019/3/12
     */
    List<GCity> selectByEmpId(Long empId);

    /**
     * 查询对应权限下的城市数据列表
     * @param inData
     * @author ws
     * @return
     */
    List<GetCityOutData> selectListByCondition(GetCityInData inData);

    /**
     * 查询城市数据列表
     * @param inData
     * @author ws
     * @return
     */
    List<GCity> selectCitys(GetCityInData inData);
}