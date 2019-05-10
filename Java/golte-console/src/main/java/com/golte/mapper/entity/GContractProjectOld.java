package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_contract_project_old")
public class GContractProjectOld implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APPROVAL_ID")
    private Long approvalId;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "POINT_ID")
    private Long pointId;

    @Column(name = "TOLL_MODE")
    private String tollMode;

    @Column(name = "PAYMENT_CYCLE")
    private String paymentCycle;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CONTRACT_PROJECT_ID")
    private Long contractProjectId;

    @Column(name = "UNIT_NAME")
    private String unitName;

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
     * @return TOLL_MODE
     */
    public String getTollMode() {
        return tollMode;
    }

    /**
     * @param tollMode
     */
    public void setTollMode(String tollMode) {
        this.tollMode = tollMode;
    }

    /**
     * @return PAYMENT_CYCLE
     */
    public String getPaymentCycle() {
        return paymentCycle;
    }

    /**
     * @param paymentCycle
     */
    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    /**
     * @return UNIT_PRICE
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return NUMBER
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return SUBTOTAL
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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
     * @return UNIT_NAME
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}