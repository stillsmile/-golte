package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_area")
public class GArea implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AREA_NO")
    private String areaNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "F_ID")
    private Long fId;

    @Column(name = "F_NO")
    private String fNo;

    @Column(name = "AREA_STATUS")
    private String areaStatus;

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
     * @return AREA_NO
     */
    public String getAreaNo() {
        return areaNo;
    }

    /**
     * @param areaNo
     */
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return F_ID
     */
    public Long getfId() {
        return fId;
    }

    /**
     * @param fId
     */
    public void setfId(Long fId) {
        this.fId = fId;
    }

    /**
     * @return F_NO
     */
    public String getfNo() {
        return fNo;
    }

    /**
     * @param fNo
     */
    public void setfNo(String fNo) {
        this.fNo = fNo;
    }

    /**
     * @return AREA_STATUS
     */
    public String getAreaStatus() {
        return areaStatus;
    }

    /**
     * @param areaStatus
     */
    public void setAreaStatus(String areaStatus) {
        this.areaStatus = areaStatus;
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