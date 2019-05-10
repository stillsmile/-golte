package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_resource_project_detail")
public class GResourceProjectDetail implements Serializable {
    @Id
    @Column(name = "ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "POINT_ID")
    private Long pointId;

    @Column(name = "ASSETS_CODE")
    private String assetsCode;

    @Column(name = "ASSETS_NAME")
    private String assetsName;

    @Column(name = "ASSETS_UNIT")
    private String assetsUnit;

    @Column(name = "DEL_STATUS")
    private String delStatus;

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
     * @return PROJECT_ID
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return POINT_ID
     */
    public Long getPointId() {
        return pointId;
    }

    /**
     * @param pointId
     */
    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    /**
     * @return ASSETS_CODE
     */
    public String getAssetsCode() {
        return assetsCode;
    }

    /**
     * @param assetsCode
     */
    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
    }

    /**
     * @return ASSETS_NAME
     */
    public String getAssetsName() {
        return assetsName;
    }

    /**
     * @param assetsName
     */
    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    /**
     * @return ASSETS_UNIT
     */
    public String getAssetsUnit() {
        return assetsUnit;
    }

    /**
     * @param assetsUnit
     */
    public void setAssetsUnit(String assetsUnit) {
        this.assetsUnit = assetsUnit;
    }

    /**
     * @return DEL_STATUS
     */
    public String getDelStatus() {
        return delStatus;
    }

    /**
     * @param delStatus
     */
    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
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