package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_city")
public class GCity implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CENTRAL_CITY_ID")
    private Long centralCityId;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "CITY_PRINCIPAL")
    private Long cityPrincipal;

    @Column(name = "BUSINESS_PRINCIPAL")
    private Long businessPrincipal;

    @Column(name = "YEAR_TARGET")
    private BigDecimal yearTarget;

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
     * @return CENTRAL_CITY_ID
     */
    public Long getCentralCityId() {
        return centralCityId;
    }

    /**
     * @param centralCityId
     */
    public void setCentralCityId(Long centralCityId) {
        this.centralCityId = centralCityId;
    }

    /**
     * @return CITY_NAME
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return CITY_PRINCIPAL
     */
    public Long getCityPrincipal() {
        return cityPrincipal;
    }

    /**
     * @param cityPrincipal
     */
    public void setCityPrincipal(Long cityPrincipal) {
        this.cityPrincipal = cityPrincipal;
    }

    /**
     * @return BUSINESS_PRINCIPAL
     */
    public Long getBusinessPrincipal() {
        return businessPrincipal;
    }

    /**
     * @param businessPrincipal
     */
    public void setBusinessPrincipal(Long businessPrincipal) {
        this.businessPrincipal = businessPrincipal;
    }

    /**
     * @return YEAR_TARGET
     */
    public BigDecimal getYearTarget() {
        return yearTarget;
    }

    /**
     * @param yearTarget
     */
    public void setYearTarget(BigDecimal yearTarget) {
        this.yearTarget = yearTarget;
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