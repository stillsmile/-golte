package com.golte.system.service.data;

import com.golte.common.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取资源点位返回结果
 *
 * @author Administrator
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResourceOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 权限id
    private Long id;

    // 权限名称
    private String name;

    // 权限url
    private String url;

    // 父节点（0最高节点）
    private Long pid;

    // 是否选中
    private boolean checked = false;

    // 是否展开
    private boolean open = false;

    // 子节点
    private List<GetResourceOutData> nodes;

    // 增加child
    public void addChild(GetResourceOutData child) {
        if (Util.isEmptyList(this.nodes)) {
            this.nodes = new ArrayList<GetResourceOutData>();
        }
        this.nodes.add(child);
    }
}
