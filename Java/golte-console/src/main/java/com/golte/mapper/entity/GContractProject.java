package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_contract_project")
public class GContractProject implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "CHANGE_PAYBACK_AMOUNT")
    private BigDecimal changePaybackAmount;

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
     * @return CHANGE_PAYBACK_AMOUNT
     */
    public BigDecimal getChangePaybackAmount() {
        return changePaybackAmount;
    }

    /**
     * @param changePaybackAmount
     */
    public void setChangePaybackAmount(BigDecimal changePaybackAmount) {
        this.changePaybackAmount = changePaybackAmount;
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