package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 角色授权请求
 *
 * @author wj@163.com
 * @date 2018/5/29 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAuthorizationRoleInData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long id;

    /**
     * 权限id集合
     */
    private List<Long> resourceIds;
}
