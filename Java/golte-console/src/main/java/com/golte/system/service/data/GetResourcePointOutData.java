package com.golte.system.service.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResourcePointOutData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ID
    private Long id;
    
    //点位名称
    private String name;
    
    //父节点ID
    private Long parentId;

    //根据三节点查一二三节点Id
    private Long firstParentId;
    
    //删除状态（0 删除 1未删除）
    private String delStatus;
    
    // 角色Id集合
    private List<Long> roleIds;
    // 角色名称集合
    private List<String> roleNames;

}
