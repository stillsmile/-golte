package com.golte.system.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.*;
import com.golte.mapper.entity.GMessagePush;
import com.golte.system.service.MessageService;
import com.golte.system.service.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private GMessagePushMapper messagePushMapper;

    @Override
    public JsonResult selectUnReadMesList(GetMessagePushInData inData) throws Exception {

        PageInfo<GetMessagePushOutData> pageInfo;

        Example example = new Example(GMessagePush.class);
        example.createCriteria().andEqualTo("mesReceiveEmpId",inData.getId());
        List<GMessagePush> gMessagePushes = messagePushMapper.selectByExample(example);
        for (GMessagePush messagePush : gMessagePushes) {
            messagePush.setMesStatus("1");
            messagePushMapper.updateByPrimaryKeySelective(messagePush);
        }

        List<GetMessagePushOutData> messagePushOutData = messagePushMapper.selectUnReadMesList(inData);

        if (Util.isEmptyList(messagePushOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetMessagePushOutData>());
        } else {
            pageInfo = new PageInfo<>(messagePushOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

}
