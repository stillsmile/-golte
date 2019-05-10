package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_contract_up")
public class GContractUp implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MERCHANT_ID")
    private Long merchantId;

    @Column(name = "MERCHANT_CONTACT")
    private String merchantContact;

    @Column(name = "MERCHANT_CONTACT_PHONE")
    private String merchantContactPhone;

    @Column(name = "SIGNING_CONTACT")
    private String signingContact;

    @Column(name = "SIGNING_CONTACT_PHONE")
    private String signingContactPhone;

    @Column(name = "SIGNING_TIME")
    private Date signingTime;

    @Column(name = "DEADLINE_START_TIME")
    private Date deadlineStartTime;

    @Column(name = "DEADLINE_END_TIME")
    private Date deadlineEndTime;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "MARGIN")
    private BigDecimal margin;

    @Column(name = "SIGNING_TYPE")
    private String signingType;

    @Column(name = "CONTRACT_TYPE")
    private String contractType;

    @Column(name = "DARFT_STATUS")
    private String darftStatus;

    @Column(name = "PAYBACK_TYPE")
    private String paybackType;

    @Column(name = "CONTRACT_STATUS")
    private String contractStatus;

    @Column(name = "DEL_STATUS")
    private String delStatus;

    @Column(name = "APPROVAL_TIME")
    private Date approvalTime;

    @Column(name = "TERMINATION_TIME")
    private Date terminationTime;

    @Column(name = "TERMINATION_REASON")
    private String terminationReason;

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

    @Column(name = "APPROVAL_ID")
    private Long approvalId;

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
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return MERCHANT_CONTACT
     */
    public String getMerchantContact() {
        return merchantContact;
    }

    /**
     * @param merchantContact
     */
    public void setMerchantContact(String merchantContact) {
        this.merchantContact = merchantContact;
    }

    /**
     * @return MERCHANT_CONTACT_PHONE
     */
    public String getMerchantContactPhone() {
        return merchantContactPhone;
    }

    /**
     * @param merchantContactPhone
     */
    public void setMerchantContactPhone(String merchantContactPhone) {
        this.merchantContactPhone = merchantContactPhone;
    }

    /**
     * @return SIGNING_CONTACT
     */
    public String getSigningContact() {
        return signingContact;
    }

    /**
     * @param signingContact
     */
    public void setSigningContact(String signingContact) {
        this.signingContact = signingContact;
    }

    /**
     * @return SIGNING_CONTACT_PHONE
     */
    public String getSigningContactPhone() {
        return signingContactPhone;
    }

    /**
     * @param signingContactPhone
     */
    public void setSigningContactPhone(String signingContactPhone) {
        this.signingContactPhone = signingContactPhone;
    }

    /**
     * @return SIGNING_TIME
     */
    public Date getSigningTime() {
        return signingTime;
    }

    /**
     * @param signingTime
     */
    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }

    /**
     * @return DEADLINE_START_TIME
     */
    public Date getDeadlineStartTime() {
        return deadlineStartTime;
    }

    /**
     * @param deadlineStartTime
     */
    public void setDeadlineStartTime(Date deadlineStartTime) {
        this.deadlineStartTime = deadlineStartTime;
    }

    /**
     * @return DEADLINE_END_TIME
     */
    public Date getDeadlineEndTime() {
        return deadlineEndTime;
    }

    /**
     * @param deadlineEndTime
     */
    public void setDeadlineEndTime(Date deadlineEndTime) {
        this.deadlineEndTime = deadlineEndTime;
    }

    /**
     * @return AMOUNT
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return MARGIN
     */
    public BigDecimal getMargin() {
        return margin;
    }

    /**
     * @param margin
     */
    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    /**
     * @return SIGNING_TYPE
     */
    public String getSigningType() {
        return signingType;
    }

    /**
     * @param signingType
     */
    public void setSigningType(String signingType) {
        this.signingType = signingType;
    }

    /**
     * @return CONTRACT_TYPE
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * @param contractType
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * @return DARFT_STATUS
     */
    public String getDarftStatus() {
        return darftStatus;
    }

    /**
     * @param darftStatus
     */
    public void setDarftStatus(String darftStatus) {
        this.darftStatus = darftStatus;
    }

    /**
     * @return PAYBACK_TYPE
     */
    public String getPaybackType() {
        return paybackType;
    }

    /**
     * @param paybackType
     */
    public void setPaybackType(String paybackType) {
        this.paybackType = paybackType;
    }

    /**
     * @return CONTRACT_STATUS
     */
    public String getContractStatus() {
        return contractStatus;
    }

    /**
     * @param contractStatus
     */
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
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
     * @return APPROVAL_TIME
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * @param approvalTime
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * @return TERMINATION_TIME
     */
    public Date getTerminationTime() {
        return terminationTime;
    }

    /**
     * @param terminationTime
     */
    public void setTerminationTime(Date terminationTime) {
        this.terminationTime = terminationTime;
    }

    /**
     * @return TERMINATION_REASON
     */
    public String getTerminationReason() {
        return terminationReason;
    }

    /**
     * @param terminationReason
     */
    public void setTerminationReason(String terminationReason) {
        this.terminationReason = terminationReason;
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
     * @return APPROVAL_ID
     */
    public Long getApprovalId() {
        return approvalId;
    }

    /**
     * @param approvalId
     */
    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
    }
}