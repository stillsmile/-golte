package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GMessagePush;
import com.golte.system.service.data.GetMessagePushInData;
import com.golte.system.service.data.GetMessagePushOutData;

import java.util.List;

public interface GMessagePushMapper extends BaseMapper<GMessagePush> {
    /**
     * 未读信息查询
     * @param inData
     * @return
     */
    List<GetMessagePushOutData> selectUnReadMesList(GetMessagePushInData inData);
}