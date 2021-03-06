package com.golte.common.data;

import java.io.Serializable;
import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * 
 * @author  作者  GXS
 * @date    创建时间  2018年8月28日 下午4:39:25  
 * @description 图片存储的路径
 */

@Data
@AllArgsConstructor
public class GetPicturePathOutData implements Serializable{
	private static final long serialVersionUID = -6699961544446107049L;

	// 图片的服务器访问路径
	private String serverPath;

	// 图片的文件系统保存路径
	private Path storagePath;
}
