package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_role")
public class GRole implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "ROLE_DES")
    private String roleDes;

    @Column(name = "ROLE_SORT_VALUE")
    private Integer roleSortValue;

    @Column(name = "ROLE_STATUS")
    private String roleStatus;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CREATE_NAME")
    private String createName;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_NAME")
    private String updateName;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return ROLE_NAME
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return ROLE_DES
     */
    public String getRoleDes() {
        return roleDes;
    }

    /**
     * @param roleDes
     */
    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }

    /**
     * @return ROLE_SORT_VALUE
     */
    public Integer getRoleSortValue() {
        return roleSortValue;
    }

    /**
     * @param roleSortValue
     */
    public void setRoleSortValue(Integer roleSortValue) {
        this.roleSortValue = roleSortValue;
    }

    /**
     * @return ROLE_STATUS
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * @param roleStatus
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return CREATE_NAME
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * @param createName
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return UPDATE_NAME
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * @param updateName
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
}