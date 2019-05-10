package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_payback_approval")
public class GPaybackApproval implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "PAYBACK_PLAN_ID")
    private Long paybackPlanId;

    @Column(name = "EXTENSION_DAY_NUM")
    private Integer extensionDayNum;

    @Column(name = "EXTENSION_AMOUNT")
    private BigDecimal extensionAmount;

    @Column(name = "EXTENSION_REASON")
    private String extensionReason;

    @Column(name = "SUPPER_EMPLOYEE_ID")
    private String supperEmployeeId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "OPINION")
    private String opinion;

    @Column(name = "EXTENSION_TIME")
    private Date extensionTime;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "APPROVAL_TIME")
    private Date approvalTime;

    @Column(name = "APPROVE_EMPLOYEE_ID")
    private Long approveEmployeeId;

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
     * @return PAYBACK_PLAN_ID
     */
    public Long getPaybackPlanId() {
        return paybackPlanId;
    }

    /**
     * @param paybackPlanId
     */
    public void setPaybackPlanId(Long paybackPlanId) {
        this.paybackPlanId = paybackPlanId;
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
     * @return EXTENSION_REASON
     */
    public String getExtensionReason() {
        return extensionReason;
    }

    /**
     * @param extensionReason
     */
    public void setExtensionReason(String extensionReason) {
        this.extensionReason = extensionReason;
    }

    /**
     * @return SUPPER_EMPLOYEE_ID
     */
    public String getSupperEmployeeId() {
        return supperEmployeeId;
    }

    /**
     * @param supperEmployeeId
     */
    public void setSupperEmployeeId(String supperEmployeeId) {
        this.supperEmployeeId = supperEmployeeId;
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
     * @return OPINION
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * @param opinion
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
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
     * @return APPROVE_EMPLOYEE_ID
     */
    public Long getApproveEmployeeId() {
        return approveEmployeeId;
    }

    /**
     * @param approveEmployeeId
     */
    public void setApproveEmployeeId(Long approveEmployeeId) {
        this.approveEmployeeId = approveEmployeeId;
    }
}