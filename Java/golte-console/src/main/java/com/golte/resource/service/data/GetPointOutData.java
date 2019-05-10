package com.golte.resource.service.data;

import com.golte.common.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPointOutData implements Serializable{
	private static final long serialVersionUID = 1L;

    //ID
    private Long id;

    //点位名称
    private String name;

    //父节点ID
    private Long parentId;

    //删除状态（0 删除 1未删除）
    private String delStatus;

    //资源等级
    private Integer lev;

}
