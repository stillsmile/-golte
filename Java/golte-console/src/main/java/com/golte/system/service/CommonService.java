package com.golte.system.service;

import com.golte.common.data.GetPicturePathOutData;

public interface CommonService {

	/**
	 * 
	 * @author 作者 GXS
	 * @date 创建时间 2018年7月23日 下午8:13:43
	 * @Description 获取上传图片路径
	 * @param @param imageName
	 * @param @return
	 * @return ImagePath
	 */
	GetPicturePathOutData createUploadPath(String pictureName) throws Exception;

	/**
	 * 
	 * @author 作者 GXS
	 * @date 创建时间 2018年7月23日 下午8:14:22
	 * @Description 保存图片
	 * @param @param bytes
	 * @param @param imagePath
	 * @param @throws Exception
	 * @return void
	 */
	void savePicture(byte[] bytes, GetPicturePathOutData picturePath) throws Exception;

	/**
	 * 发送邮件
	 * @param toMail
	 * @param msg
	 * @param subject
	 * @param cc 抄送
	 * @throws Exception
	 */
	void sendMail(String toMail, String msg, String subject,String[] cc) throws Exception;
}
