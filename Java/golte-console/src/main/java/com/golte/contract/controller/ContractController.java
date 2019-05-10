package com.golte.contract.controller;

import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.contract.service.ContractService;
import com.golte.contract.service.data.GetContractExportInData;
import com.golte.contract.service.data.GetContractInData;
import com.golte.system.service.data.GetLoginOutData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 作者 wyf
 * @date 创建时间 2019年3月25日
 * @description 合同管理
 */
@RestController
@RequestMapping(value = "/pc/contract/")
public class ContractController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	private ContractService contractService;

	/**
     * @author 作者 wyf
     * @date 创建时间 2019年3月26日
	 * @description 查询合同列表（分页）
	 * @param inData
	 * @return
     */
	@PostMapping("select")
	public JsonResult selectContractList(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetCityOutData cityOutData = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
			inData.setCityIds(cityOutData.getCityIds());
			return contractService.selectContractList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 查询合同详情
	 * @param inData
	 * @return
	 */
	@PostMapping("detail")
	public JsonResult detailContract(@RequestBody GetContractInData inData) {
		try {
			return contractService.detailContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 新增合同
	 * @param inData
	 * @return
	 */
	@PostMapping("add")
	public JsonResult addContract(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setLoginName(loginOutData.getLoginName());
			return contractService.addContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 编辑合同
	 * @param inData
	 * @return
	 */
	@PostMapping("update")
	public JsonResult updateContract(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setLoginName(loginOutData.getLoginName());
			return contractService.updateContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 终止合同
	 * @param inData
	 * @return
	 */
	@PostMapping("stop")
	public JsonResult stopContract(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setEmpId(loginOutData.getEmpId());
			return contractService.stopContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取终止审批信息
	 * @param inData
	 * @return
	 */
	@PostMapping("stopDetail")
	public JsonResult stopContractDetail(@RequestBody GetContractInData inData) {
		try {
			return contractService.stopContractDetail(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 审批终止合同
	 * @param inData
	 * @return
	 */
	@PostMapping("approveStop")
	public JsonResult approveStopContract(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setEmpId(loginOutData.getEmpId());
			return contractService.approveStopContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 变更合同
	 * @param inData
	 * @return
	 */
	@PostMapping("change")
	public JsonResult changeContract(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
            GetLoginOutData loginOutData = getLoginUser(request);
            inData.setEmpId(loginOutData.getEmpId());
			return contractService.changeContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

    /**
     * @author 作者 wyf
     * @date 创建时间 2019年3月26日
     * @description 获取变更审批信息
     * @param inData
     * @return
     */
    @PostMapping("changeDetail")
    public JsonResult changeContractDetail(@RequestBody GetContractInData inData) {
        try {
            return contractService.changeContractDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 审批变更合同
	 * @param inData
	 * @return
	 */
	@PostMapping("approveChange")
	public JsonResult approveChangeContract(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetLoginOutData loginOutData = getLoginUser(request);
			inData.setEmpId(loginOutData.getEmpId());
			return contractService.approveChangeContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 删除合同
	 * @param inData
	 * @return
	 */
	@PostMapping("delete")
	public JsonResult deleteContract(@RequestBody GetContractInData inData) {
		try {
			return contractService.deleteContract(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取城市列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getCityList")
	public JsonResult getCityList(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetCityOutData cityOutData = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
			inData.setCityIds(cityOutData.getCityIds());
			return contractService.getCityList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取城市列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getCityListByCenterCity")
	public JsonResult getCityListByCenterCity(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			return contractService.getCityListByCenterCity(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取中心城市列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getCenterCityList")
	public JsonResult getCenterCityList(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetCityOutData cityOutData = getCentralCityInfo(request);
			inData.setCenterCityIds(cityOutData.getCentralCityIds());
			return contractService.getCenterCityList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取商家列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getMerchantList")
	public JsonResult getMerchantList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getMerchantList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取合同列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getContractList")
	public JsonResult getContractList(HttpServletRequest request,@RequestBody GetContractInData inData) {
		try {
			GetCityOutData cityOutData = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
			inData.setCityIds(cityOutData.getCityIds());
			return contractService.getContractList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取项目列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getProjectList")
	public JsonResult getProjectList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getProjectList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取一级点位列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getPointList")
	public JsonResult getPointList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getPointList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取二级点位列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getPointTwoList")
	public JsonResult getPointTwoList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getPointTwoList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取三级点位列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getPointThreeList")
	public JsonResult getPointThreeList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getPointThreeList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取子点位列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getPointChildList")
	public JsonResult getPointChildList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getPointChildList(inData.getPointId());
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取资源列表
	 * @param inData
	 * @return
	 */
	@PostMapping("getResourceList")
	public JsonResult getResourceList(@RequestBody GetContractInData inData) {
		try {
			return contractService.getResourceList(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 导出合同报表
	 * @param inData
	 * @return
	 */
	@RequestMapping(value = "exportOut", method = RequestMethod.POST)
	public void exportOut(HttpServletRequest request, HttpServletResponse response, GetContractInData inData) {
		try {
			GetCityOutData cityOutData = getCityInfo(inData.getToken());
			inData.setCityIds(cityOutData.getCityIds());
			contractService.exportOut(response,inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @Description: 批量导入合同
	 * @author wuyifeng
	 * @date 2018/10/23
	 */
	@RequestMapping(value = "exportIn", method = RequestMethod.POST)
	public JsonResult exportIn(HttpServletRequest request, MultipartFile file) {
		try {
			GetLoginOutData outData = getLoginUser(request);
			String loginName = outData.getLoginName();
			List<GetContractExportInData> inData = importExcel(file, GetContractExportInData.class, 0,2);
			return contractService.exportIn(inData,loginName);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * @Description: 获取未回款金额
	 * @author wuyifeng
	 * @date 2018/10/23
	 */
	@RequestMapping(value = "getAmount", method = RequestMethod.POST)
	public JsonResult getAmount(@RequestBody GetContractInData inData) {
		try {
			return contractService.getAmount(inData);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

}
