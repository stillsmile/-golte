package com.golte.payBack.controller;

import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.contract.service.ContractService;
import com.golte.contract.service.data.GetContractInData;
import com.golte.payBack.service.PayBackPlanService;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanListInData;
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
@RequestMapping(value = "/pc/payBackPlan/")
public class PayBackPlanController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(PayBackPlanController.class);

	@Autowired
	private PayBackPlanService payBackPlanService;

	/**
     * @author 作者 wyf
     * @date 创建时间 2019年3月26日
	 * @description 查询回款计划列表（分页）
	 * @param inData
	 * @return
     */
	@PostMapping("select")
	public JsonResult selectPayBackList(HttpServletRequest request,@RequestBody GetPayBackPlanInData inData) {
		try {
			GetCityOutData cityOutData = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
			inData.setCityIds(cityOutData.getCityIds());
			return payBackPlanService.selectPayBackList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询回款计划详情
	 * @param inData
	 * @return
	 */
	@PostMapping("detail")
	public JsonResult detailPayBack(@RequestBody GetPayBackPlanInData inData) {
		try {
			return payBackPlanService.detailPayBack(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 编辑回款计划
	 * @param inData
	 * @return
	 */
	@PostMapping("update")
	public JsonResult updatePayBack(@RequestBody GetPayBackPlanListInData inData) {
		try {
			return payBackPlanService.updatePayBack(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

}
