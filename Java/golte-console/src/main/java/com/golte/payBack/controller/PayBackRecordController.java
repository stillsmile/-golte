package com.golte.payBack.controller;

import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.payBack.service.PayBackRecordService;
import com.golte.payBack.service.data.GetPayBackRecordInData;
import com.golte.payBack.service.data.GetPayBackRecordListInData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 作者 wyf
 * @date 创建时间 2019年3月25日
 * @description 回款管理
 */
@RestController
@RequestMapping(value = "/pc/payBackRecord/")
public class PayBackRecordController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(PayBackRecordController.class);

	@Autowired
	private PayBackRecordService payBackRecordService;

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询回款记录详情
	 * @param inData
	 * @return
	 */
	@PostMapping("detail")
	public JsonResult detailPayBackRecord(@RequestBody GetPayBackRecordInData inData) {
		try {
			return payBackRecordService.detailPayBackRecord(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询合同回款记录详情
	 * @param inData
	 * @return
	 */
	@PostMapping("detailRecords")
	public JsonResult detailPayBackRecordList(@RequestBody GetPayBackRecordInData inData) {
		try {
			return payBackRecordService.detailPayBackRecordList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 新增回款记录
	 * @param inData
	 * @return
	 */
	@PostMapping("update")
	public JsonResult updatePayBackRecord(@RequestBody GetPayBackRecordListInData inData) {
		try {
			return payBackRecordService.updatePayBackRecord(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 获取收款人列表
	 * @return
	 */
	@PostMapping("getEmpList")
	public JsonResult getEmpList() {
		try {
			return payBackRecordService.getEmpList();
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
}
