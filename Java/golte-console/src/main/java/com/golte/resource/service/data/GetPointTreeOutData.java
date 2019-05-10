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
public class GetPointTreeOutData implements Serializable{
	private static final long serialVersionUID = 1L;

    // 权限id
    private Long id;

    // 权限名称
    private String name;

    //资源等级
    private Integer level;

    // 父节点（0最高节点）
    private Long pid;

    // 父节点名称
    private String pname;

    // 是否选中
    private boolean checked = false;

    // 是否展开
    private boolean open = false;

    // 子节点
    private List<GetPointTreeOutData> nodes;

    // 增加child
    public void addChild(GetPointTreeOutData child) {
        if (Util.isEmptyList(this.nodes)) {
            this.nodes = new ArrayList<GetPointTreeOutData>();
        }
        this.nodes.add(child);
    }

}
