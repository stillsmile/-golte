package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_contract_approval")
public class GContractApproval implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SUPPER_EMPLOYEE_ID")
    private String supperEmployeeId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "OPINION")
    private String opinion;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "APPROVAL_TIME")
    private Date approvalTime;

    @Column(name = "CREATE_NAME")
    private String createName;

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
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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