package com.golte.system.service.impl;

import com.golte.common.data.GetPictureConfigInData;
import com.golte.common.data.GetPicturePathOutData;
import com.golte.common.util.Util;
import com.golte.system.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private GetPictureConfigInData pictureConfig;

	@Autowired
	JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public GetPicturePathOutData createUploadPath(String pictureName) throws Exception {
		// 上传文件的保存路径
		Path storePath = Paths.get(pictureConfig.getBasePath(), pictureConfig.getUploadPath(), pictureName);
		// 上传文件的显示路径
		String serverPath = new StringBuilder(pictureConfig.getServerBasePath()).append("/").append(pictureConfig.getUploadPath()).append("/")
		        .append(pictureName).toString();
		return new GetPicturePathOutData(serverPath, storePath);
	}

	@Override
	public void savePicture(byte[] bytes, GetPicturePathOutData picturePath) throws Exception {
		Path storePath = picturePath.getStoragePath();
		Path parentPath = storePath.getParent();
		if (!Files.exists(parentPath)) {
			Files.createDirectories(parentPath);
		}
		Files.write(storePath, bytes, StandardOpenOption.CREATE);

	}

	@Override
	public void sendMail(String toMail, String msg, String subject,String[] cc) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		// 发送者邮箱
		message.setFrom(sender);
		// 接受者邮箱
		message.setTo(toMail);
		if (!Util.isEmpty(cc)) {
			message.setCc(cc);
		}
		// 主题
		message.setSubject(subject);
		// 内容
		message.setText(msg);
		mailSender.send(message);
	}

}
