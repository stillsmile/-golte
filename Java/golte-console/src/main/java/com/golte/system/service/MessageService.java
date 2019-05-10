package com.golte.system.service;

import com.golte.common.data.JsonResult;
import com.golte.system.service.data.GetMessagePushInData;

public interface MessageService {
    /**
     * 指标信息查询
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectUnReadMesList(GetMessagePushInData inData) throws Exception;

}
