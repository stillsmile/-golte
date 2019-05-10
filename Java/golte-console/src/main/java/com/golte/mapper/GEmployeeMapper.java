package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GEmployee;
import com.golte.system.service.data.GetEmployeeInData;
import com.golte.system.service.data.GetEmployeeOutData;

import java.util.List;

public interface GEmployeeMapper extends BaseMapper<GEmployee> {

    /**
     * 查询数据
     * @param inData
     * @return
     */
    List<GetEmployeeOutData> selectListByCondition(GetEmployeeInData inData);
}