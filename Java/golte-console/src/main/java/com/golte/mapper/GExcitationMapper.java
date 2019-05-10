package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.excitation.service.data.ExcitationInData;
import com.golte.excitation.service.data.ExcitationOutData;
import com.golte.mapper.entity.GExcitation;

import java.util.List;

public interface GExcitationMapper extends BaseMapper<GExcitation> {

    /**
     * 激励信息列表
     * @param inData
     * @author ws
     * @return
     */
    List<ExcitationOutData> selectListByCondition(ExcitationInData inData);

}