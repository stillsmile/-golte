package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_payback_record")
public class GPaybackRecord implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "PAYBACK_PLAN_ID")
    private Long paybackPlanId;

    @Column(name = "CONTRACT_PROJECT_ID")
    private Long contractProjectId;

    @Column(name = "PAYBACK_AMOUNT")
    private BigDecimal paybackAmount;

    @Column(name = "PAYBACK_INVOICE_CODE")
    private String paybackInvoiceCode;

    @Column(name = "LAST_PAYMENT")
    private BigDecimal lastPayment;

    @Column(name = "RECEIPT_MEMBER")
    private String receiptMember;

    @Column(name = "PAYBACK_TIME")
    private Date paybackTime;

    @Column(name = "CREATE_NAME")
    private String createName;

    @Column(name = "UPDATE_NAME")
    private String updateName;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

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
     * @return PAYBACK_AMOUNT
     */
    public BigDecimal getPaybackAmount() {
        return paybackAmount;
    }

    /**
     * @param paybackAmount
     */
    public void setPaybackAmount(BigDecimal paybackAmount) {
        this.paybackAmount = paybackAmount;
    }

    /**
     * @return PAYBACK_INVOICE_CODE
     */
    public String getPaybackInvoiceCode() {
        return paybackInvoiceCode;
    }

    /**
     * @param paybackInvoiceCode
     */
    public void setPaybackInvoiceCode(String paybackInvoiceCode) {
        this.paybackInvoiceCode = paybackInvoiceCode;
    }

    /**
     * @return LAST_PAYMENT
     */
    public BigDecimal getLastPayment() {
        return lastPayment;
    }

    /**
     * @param lastPayment
     */
    public void setLastPayment(BigDecimal lastPayment) {
        this.lastPayment = lastPayment;
    }

    /**
     * @return RECEIPT_MEMBER
     */
    public String getReceiptMember() {
        return receiptMember;
    }

    /**
     * @param receiptMember
     */
    public void setReceiptMember(String receiptMember) {
        this.receiptMember = receiptMember;
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
}