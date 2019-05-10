package com.golte.system.service.data;

import com.golte.common.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取权限返回结果
 *
 * @author wj@163.com
 * @date 2018/5/30 9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMenuOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 权限id
    private Long id;

    // 权限名称
    private String name;

    // 权限url
    private String url;

    // 权限图标
    private String icon;

    // 父节点（0最高节点）
    private Long pid;

    // 子节点
    private List<GetMenuOutData> nodes;

    // 增加child
    public void addChild(GetMenuOutData child) {
        if (Util.isEmpty(this.nodes)) {
            this.nodes = new ArrayList<GetMenuOutData>();
        }
        this.nodes.add(child);
    }
}
