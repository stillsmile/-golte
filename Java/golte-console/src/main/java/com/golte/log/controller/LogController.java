package com.golte.log.controller;

import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.log.service.LogService;
import com.golte.log.service.data.GetLogInData;
import com.golte.payBack.service.PayBackPlanService;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanListInData;
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
 * @description 日志管理
 */
@RestController
@RequestMapping(value = "/pc/log/")
public class LogController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(LogController.class);

	@Autowired
	private LogService logService;

	/**
     * @author 作者 wyf
     * @date 创建时间 2019年3月26日
	 * @description 查询日志列表（分页）
	 * @param inData
	 * @return
     */
	@PostMapping("select")
	public JsonResult selectLogList(@RequestBody GetLogInData inData) {
		try {
			return logService.selectLogList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取变更日志详情
	 * @param inData
	 * @return
	 */
	@PostMapping("detail")
	public JsonResult getLogDetail(@RequestBody GetLogInData inData) {
		try {
			return logService.getLogDetail(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

}
