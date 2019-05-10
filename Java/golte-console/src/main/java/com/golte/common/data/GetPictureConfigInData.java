package com.golte.common.data;

import lombok.Data;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
@ConfigurationProperties("pict")
@ToString
public class GetPictureConfigInData implements Serializable{
	private static final long serialVersionUID = -6442954708612981824L;

	// 图片存储根目录

	private String basePath;

	// 图片访问根目录
	private String serverBasePath;

	// 上传目录
	private String uploadPath;

}
