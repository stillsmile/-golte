package com.golte.resource.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 资源点位返回结果
 * @author Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPointInData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ID
    private Long id;
    
    //点位名称
    private String name;

    //项目Id
    private Long projectId;

    //父节点ID
    private Long parentId;
    
    //删除状态（0 删除 1未删除）
    private String delStatus;
    
  //资源等级
    private Integer level;
    
 // 操作人[默认取当前登录人]
    private String loginAccount;

    //删除点位资源的ID集合
    private List<Long> ids;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;
}
