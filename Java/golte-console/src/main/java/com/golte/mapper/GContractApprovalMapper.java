package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.log.service.data.GetLogInData;
import com.golte.log.service.data.GetLogOutData;
import com.golte.mapper.entity.GContractApproval;

import java.util.List;

public interface GContractApprovalMapper extends BaseMapper<GContractApproval> {
    /**
     * @Description: 查询日志列表（分页）
     * @author wyf
     * @date 2019/3/26
     */
    List<GetLogOutData> selectListByCondition(GetLogInData inData);
}