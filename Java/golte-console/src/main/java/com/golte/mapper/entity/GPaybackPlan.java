package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_payback_plan")
public class GPaybackPlan implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "CENTRAL_CITY_ID")
    private Long centralCityId;

    @Column(name = "CITY_ID")
    private Long cityId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MERCHANT_ID")
    private Long merchantId;

    @Column(name = "CONTRACT_PROJECT_ID")
    private Long contractProjectId;

    @Column(name = "RECEIVABLE_PAYBACK")
    private BigDecimal receivablePayback;

    @Column(name = "PAYBACK_STATUS")
    private String paybackStatus;

    @Column(name = "EXTENSION_STATUS")
    private String extensionStatus;

    @Column(name = "BEFORE_REMIND")
    private String beforeRemind;

    @Column(name = "EXTENSION_REMIND")
    private String extensionRemind;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DEL_STATUS")
    private String delStatus;

    @Column(name = "EXTENSION_DAY_NUM")
    private Integer extensionDayNum;

    @Column(name = "EXTENSION_AMOUNT")
    private BigDecimal extensionAmount;

    @Column(name = "EXTENSION_TIME")
    private Date extensionTime;

    @Column(name = "PAYBACK_TIME")
    private Date paybackTime;

    @Column(name = "REMITTANCE_TIME")
    private Date remittanceTime;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "CREATE_NAME")
    private String createName;

    @Column(name = "UPDATE_NAME")
    private String updateName;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "EDIT_STATUS")
    private String editStatus;

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
     * @return CONTRACT_ID
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * @param contractId
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
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
     * @return CITY_ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
     * @return MERCHANT_ID
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return CONTRACT_PROJECT_ID
     */
    public Long getContractProjectId() {
        return contractProjectId;
    }

    /**
     * @param contractProjectId
     */
    public void setContractProjectId(Long contractProjectId) {
        this.contractProjectId = contractProjectId;
    }

    /**
     * @return RECEIVABLE_PAYBACK
     */
    public BigDecimal getReceivablePayback() {
        return receivablePayback;
    }

    /**
     * @param receivablePayback
     */
    public void setReceivablePayback(BigDecimal receivablePayback) {
        this.receivablePayback = receivablePayback;
    }

    /**
     * @return PAYBACK_STATUS
     */
    public String getPaybackStatus() {
        return paybackStatus;
    }

    /**
     * @param paybackStatus
     */
    public void setPaybackStatus(String paybackStatus) {
        this.paybackStatus = paybackStatus;
    }

    /**
     * @return EXTENSION_STATUS
     */
    public String getExtensionStatus() {
        return extensionStatus;
    }

    /**
     * @param extensionStatus
     */
    public void setExtensionStatus(String extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    /**
     * @return BEFORE_REMIND
     */
    public String getBeforeRemind() {
        return beforeRemind;
    }

    /**
     * @param beforeRemind
     */
    public void setBeforeRemind(String beforeRemind) {
        this.beforeRemind = beforeRemind;
    }

    /**
     * @return EXTENSION_REMIND
     */
    public String getExtensionRemind() {
        return extensionRemind;
    }

    /**
     * @param extensionRemind
     */
    public void setExtensionRemind(String extensionRemind) {
        this.extensionRemind = extensionRemind;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return EXTENSION_DAY_NUM
     */
    public Integer getExtensionDayNum() {
        return extensionDayNum;
    }

    /**
     * @param extensionDayNum
     */
    public void setExtensionDayNum(Integer extensionDayNum) {
        this.extensionDayNum = extensionDayNum;
    }

    /**
     * @return EXTENSION_AMOUNT
     */
    public BigDecimal getExtensionAmount() {
        return extensionAmount;
    }

    /**
     * @param extensionAmount
     */
    public void setExtensionAmount(BigDecimal extensionAmount) {
        this.extensionAmount = extensionAmount;
    }

    /**
     * @return EXTENSION_TIME
     */
    public Date getExtensionTime() {
        return extensionTime;
    }

    /**
     * @param extensionTime
     */
    public void setExtensionTime(Date extensionTime) {
        this.extensionTime = extensionTime;
    }

    /**
     * @return PAYBACK_TIME
     */
    public Date getPaybackTime() {
        return paybackTime;
    }

    /**
     * @param paybackTime
     */
    public void setPaybackTime(Date paybackTime) {
        this.paybackTime = paybackTime;
    }

    /**
     * @return REMITTANCE_TIME
     */
    public Date getRemittanceTime() {
        return remittanceTime;
    }

    /**
     * @param remittanceTime
     */
    public void setRemittanceTime(Date remittanceTime) {
        this.remittanceTime = remittanceTime;
    }

    /**
     * @return REMARKS
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
     * @return EDIT_STATUS
     */
    public String getEditStatus() {
        return editStatus;
    }

    /**
     * @param editStatus
     */
    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }
}