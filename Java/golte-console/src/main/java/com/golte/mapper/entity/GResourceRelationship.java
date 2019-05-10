package com.golte.mapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "g_resource_relationship")
public class GResourceRelationship implements Serializable {
    /**
     * 角色id
     */
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 资源id
     */
    @Column(name = "RESOURCE_ID")
    private Long resourceId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取角色id
     *
     * @return ROLE_ID - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取资源id
     *
     * @return RESOURCE_ID - 资源id
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源id
     *
     * @param resourceId 资源id
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}