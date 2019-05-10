package com.golte.payBack.controller;

import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.payBack.service.PayBackApprovalService;
import com.golte.payBack.service.PayBackRecordService;
import com.golte.payBack.service.data.GetPayBackApprovalInData;
import com.golte.payBack.service.data.GetPayBackRecordInData;
import com.golte.payBack.service.data.GetPayBackRecordListInData;
import com.golte.system.service.data.GetLoginOutData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者 wyf
 * @date 创建时间 2019年3月25日
 * @description 回款管理
 */
@RestController
@RequestMapping(value = "/pc/payBackApproval/")
public class PayBackApprovalController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(PayBackApprovalController.class);

	@Autowired
	private PayBackApprovalService payBackApprovalService;

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询延期详情
	 * @param inData
	 * @return
	 */
	@PostMapping("detail")
	public JsonResult detailPayBackApproval(@RequestBody GetPayBackApprovalInData inData) {
		try {
			return payBackApprovalService.detailPayBackApproval(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 申请延期
	 * @param inData
	 * @return
	 */
	@PostMapping("delay")
	public JsonResult delayPayBackApproval(HttpServletRequest request, @RequestBody GetPayBackApprovalInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setEmpId(loginOutData.getEmpId());
			return payBackApprovalService.delayPayBackApproval(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 延期审批
	 * @param inData
	 * @return
	 */
	@PostMapping("approve")
	public JsonResult approvePayBackApproval(HttpServletRequest request,@RequestBody GetPayBackApprovalInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setEmpId(loginOutData.getEmpId());
			return payBackApprovalService.approvePayBackApproval(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
}
