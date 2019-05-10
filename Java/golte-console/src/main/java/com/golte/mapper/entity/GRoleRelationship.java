package com.golte.mapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "g_role_relationship")
public class GRoleRelationship implements Serializable {
    /**
     * 人员id
     */
    @Column(name = "EMP_ID")
    private Long empId;

    /**
     * 角色id
     */
    @Column(name = "ROLE_ID")
    private Long roleId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取人员id
     *
     * @return EMP_ID - 人员id
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * 设置人员id
     *
     * @param empId 人员id
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

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
}