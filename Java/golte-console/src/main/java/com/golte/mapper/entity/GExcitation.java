package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_excitation")
public class GExcitation implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CONTRACT_TYPE")
    private String contractType;

    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "PROPERTY_PROFIT")
    private BigDecimal propertyProfit;

    @Column(name = "PROFIT_INDEX")
    private String profitIndex;

    @Column(name = "TAX_PERCENTAGE")
    private Double taxPercentage;

    @Column(name = "TAX")
    private BigDecimal tax;

    @Column(name = "MANAGEMENT_AMOUNT")
    private BigDecimal managementAmount;

    @Column(name = "OTHER_COST")
    private BigDecimal otherCost;

    @Column(name = "CARDINAL_NUMBER")
    private String cardinalNumber;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "EXCITATION_PERCENTAGE")
    private Double excitationPercentage;

    @Column(name = "SHOULD_AMOUNT")
    private BigDecimal shouldAmount;

    @Column(name = "ARRIVAL_CONVERSION")
    private BigDecimal arrivalConversion;

    @Column(name = "OTHER_DEDUCTION")
    private BigDecimal otherDeduction;

    @Column(name = "ACTUAL_AMOUNT")
    private BigDecimal actualAmount;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "DEL_STATUS")
    private String delStatus;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MEMBER_NAME")
    private String memberName;

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
     * @return PROPERTY_PROFIT
     */
    public BigDecimal getPropertyProfit() {
        return propertyProfit;
    }

    /**
     * @param propertyProfit
     */
    public void setPropertyProfit(BigDecimal propertyProfit) {
        this.propertyProfit = propertyProfit;
    }

    /**
     * @return PROFIT_INDEX
     */
    public String getProfitIndex() {
        return profitIndex;
    }

    /**
     * @param profitIndex
     */
    public void setProfitIndex(String profitIndex) {
        this.profitIndex = profitIndex;
    }

    /**
     * @return TAX_PERCENTAGE
     */
    public Double getTaxPercentage() {
        return taxPercentage;
    }

    /**
     * @param taxPercentage
     */
    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    /**
     * @return TAX
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * @param tax
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * @return MANAGEMENT_AMOUNT
     */
    public BigDecimal getManagementAmount() {
        return managementAmount;
    }

    /**
     * @param managementAmount
     */
    public void setManagementAmount(BigDecimal managementAmount) {
        this.managementAmount = managementAmount;
    }

    /**
     * @return OTHER_COST
     */
    public BigDecimal getOtherCost() {
        return otherCost;
    }

    /**
     * @param otherCost
     */
    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    /**
     * @return CARDINAL_NUMBER
     */
    public String getCardinalNumber() {
        return cardinalNumber;
    }

    /**
     * @param cardinalNumber
     */
    public void setCardinalNumber(String cardinalNumber) {
        this.cardinalNumber = cardinalNumber;
    }

    /**
     * @return ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return EXCITATION_PERCENTAGE
     */
    public Double getExcitationPercentage() {
        return excitationPercentage;
    }

    /**
     * @param excitationPercentage
     */
    public void setExcitationPercentage(Double excitationPercentage) {
        this.excitationPercentage = excitationPercentage;
    }

    /**
     * @return SHOULD_AMOUNT
     */
    public BigDecimal getShouldAmount() {
        return shouldAmount;
    }

    /**
     * @param shouldAmount
     */
    public void setShouldAmount(BigDecimal shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    /**
     * @return ARRIVAL_CONVERSION
     */
    public BigDecimal getArrivalConversion() {
        return arrivalConversion;
    }

    /**
     * @param arrivalConversion
     */
    public void setArrivalConversion(BigDecimal arrivalConversion) {
        this.arrivalConversion = arrivalConversion;
    }

    /**
     * @return OTHER_DEDUCTION
     */
    public BigDecimal getOtherDeduction() {
        return otherDeduction;
    }

    /**
     * @param otherDeduction
     */
    public void setOtherDeduction(BigDecimal otherDeduction) {
        this.otherDeduction = otherDeduction;
    }

    /**
     * @return ACTUAL_AMOUNT
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * @param actualAmount
     */
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     * @return MONTH
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
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
     * @return MEMBER_NAME
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * @param memberName
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
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