package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_contract_termination")
public class GContractTermination implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APPROVAL_ID")
    private Long approvalId;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "NOT_RECOVERED_AMOUNT")
    private BigDecimal notRecoveredAmount;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "TERMINATION_TIME")
    private Date terminationTime;

    @Column(name = "CREATE_TIME")
    private Date createTime;

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
     * @return NOT_RECOVERED_AMOUNT
     */
    public BigDecimal getNotRecoveredAmount() {
        return notRecoveredAmount;
    }

    /**
     * @param notRecoveredAmount
     */
    public void setNotRecoveredAmount(BigDecimal notRecoveredAmount) {
        this.notRecoveredAmount = notRecoveredAmount;
    }

    /**
     * @return REASON
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
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
}