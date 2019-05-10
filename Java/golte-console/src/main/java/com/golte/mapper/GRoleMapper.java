package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GRole;
import com.golte.system.service.data.GetRoleInData;
import com.golte.system.service.data.GetRoleOutData;

import java.util.List;

public interface GRoleMapper extends BaseMapper<GRole> {

    /**
     * 查询数据
     * @author  wangzy
     * @date 2019年3月20日
     * @Description:
     * @param inData
     * @return
     */
    List<GetRoleOutData> selectListByCondition(GetRoleInData inData);
}