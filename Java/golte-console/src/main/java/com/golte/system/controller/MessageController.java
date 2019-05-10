package com.golte.system.controller;

import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.system.service.MainService;
import com.golte.system.service.MessageService;
import com.golte.system.service.data.GetLoginOutData;
import com.golte.system.service.data.GetMainInData;
import com.golte.system.service.data.GetMessagePushInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pc/message/")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    /**
     * 指标信息查询
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("select")
    public JsonResult selectUnReadMesList(HttpServletRequest request, @RequestBody GetMessagePushInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setId(outData.getEmpId());
            return messageService.selectUnReadMesList(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

}
