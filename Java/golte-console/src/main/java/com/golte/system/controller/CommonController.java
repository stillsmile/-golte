package com.golte.system.controller;

import com.golte.common.data.GetPicturePathOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.*;
import com.golte.system.service.CommonService;
import com.golte.system.service.data.GetFileOutData;
import com.golte.system.service.data.GetVerifyOutData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 作者 GXS
 * @date 创建时间 2018年7月10日 上午11:44:07
 * @description 公用接口
 */
@RestController
@RequestMapping(value = "/common/")
public class CommonController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;

	/**
	 * 文件上传
	 *
	 * @param file 图片文件
	 * @return
	 */
	@PostMapping(value = "file/upload")
	public JsonResult imageUpload(HttpServletRequest request, MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			String regEx ="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(fileName);
			boolean b= matcher.find();
			if(b){
				return JsonResult.fail(UtilConst.CODE_403, "文件名不能包含特殊字符");
			}
			String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			GetPicturePathOutData picturePath = commonService.createUploadPath(fileName);
			picturePath.setServerPath(path + picturePath.getServerPath());
			commonService.savePicture(file.getBytes(), picturePath);
			GetFileOutData outData = new GetFileOutData();
			outData.setPath(picturePath.getServerPath());
			outData.setFileName(fileName);
			return JsonResult.success(outData, UtilMessage.UPLOAD_MESSAGE_SUCCESS);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 *图片验证码
	 * @param request
	 * @return
	 */
	@PostMapping(value = "picture/verificationCode")
	public JsonResult getPictureVerificationCode(HttpServletRequest request) {
		try {
			String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
			String base64 = VerifyCodeUtil.outputVerifyBase64(verifyCode);
			GetVerifyOutData outData = new GetVerifyOutData();
			outData.setCode(verifyCode);
			outData.setBase64(base64);
			return JsonResult.success(outData, UtilMessage.GET_MESSAGE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException(e);
		}
	}

}
