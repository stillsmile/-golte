package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作角色返回结果
 *
 * @author wj@163.com
 * @date 2018/5/29 16:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 角色id
    private Long id;

    // 角色名称
    private String roleName;

    // 角色描述
    private String roleDes;

    // 角色排序值
    private Integer roleSortValue;

    // 状态（0无效 1有效）
    private String roleStatus;

}
