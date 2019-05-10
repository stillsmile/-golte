package com.golte.system.service.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源项目返回结果
 * @author Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResourceProjectInData implements Serializable{
	private static final long serialVersionUID = 1L;
	//ID
    private Long id;
    //中心城市ID
    private Long centralCityId;
    //城市ID
    private Long cityId;
    //项目名称
    private String projectName;
    //删除状态
    private String delStatus;
    //创建时间
    private Date createTime;
    //创建人
    private String createName;
    // 操作人[默认取当前登录人]
    private String loginAccount;
    //城市ID集合
    private List<Long> cityIds;
    //页大小
    private int pageSize;
    //页码
    private int pageNum;
}
