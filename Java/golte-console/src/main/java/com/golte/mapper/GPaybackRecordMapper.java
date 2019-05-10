package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GPaybackRecord;
import com.golte.system.service.data.GetMainInData;
import com.golte.system.service.data.GetMainTopOutData;

import java.util.List;

public interface GPaybackRecordMapper extends BaseMapper<GPaybackRecord> {
    /**
     * @Description: 项目成交金额Top10
     * @author Ws
     * @date 2019/4/3
     */
    List<GetMainTopOutData> selectPaybackRecordTop10(GetMainInData inData);

    /**
     * @Description: 项目成交金额详情
     * @author Ws
     * @date 2019/4/3
     */
    List<GetMainTopOutData> selectPaybackRecordTop10Detail(GetMainInData inData);

    /**
     * @Description: 商家金额Top10
     * @author Ws
     * @date 2019/4/3
     */
    List<GetMainTopOutData> selectMerchantTop10(GetMainInData inData);

    /**
     * @Description: 商家成交金额详情
     * @author Ws
     * @date 2019/4/3
     */
    List<GetMainTopOutData> selectMerchantTop10Detail(GetMainInData inData);
}