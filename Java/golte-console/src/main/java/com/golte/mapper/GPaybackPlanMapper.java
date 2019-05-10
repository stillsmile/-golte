package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GPaybackPlan;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanOutData;

import java.util.List;

public interface GPaybackPlanMapper extends BaseMapper<GPaybackPlan> {
    /**
     * @Description: 查询回款计划列表（分页）
     * @author wyf
     * @date 2019/3/26
     */
    List<GetPayBackPlanOutData> selectListByCondition(GetPayBackPlanInData inData);
}