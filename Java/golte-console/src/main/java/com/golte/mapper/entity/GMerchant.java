package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_merchant")
public class GMerchant implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @Column(name = "CONTACT_2")
    private String contact2;

    @Column(name = "CONTACT_PHONE_2")
    private String contactPhone2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "CONPANY_ADDRESS")
    private String conpanyAddress;

    @Column(name = "DETAILED_ADDRESS")
    private String detailedAddress;

    @Column(name = "SUPPLIER_TYPE")
    private String supplierType;

    @Column(name = "REMARKS")
    private String remarks;

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

    @Column(name = "CENTRAL_CITY_ID")
    private Long centralCityId;

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
     * @return LEVEL
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return CONTACT
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return CONTACT_PHONE
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return CONTACT_2
     */
    public String getContact2() {
        return contact2;
    }

    /**
     * @param contact2
     */
    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    /**
     * @return CONTACT_PHONE_2
     */
    public String getContactPhone2() {
        return contactPhone2;
    }

    /**
     * @param contactPhone2
     */
    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

    /**
     * @return CITY
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return CONPANY_ADDRESS
     */
    public String getConpanyAddress() {
        return conpanyAddress;
    }

    /**
     * @param conpanyAddress
     */
    public void setConpanyAddress(String conpanyAddress) {
        this.conpanyAddress = conpanyAddress;
    }

    /**
     * @return DETAILED_ADDRESS
     */
    public String getDetailedAddress() {
        return detailedAddress;
    }

    /**
     * @param detailedAddress
     */
    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    /**
     * @return SUPPLIER_TYPE
     */
    public String getSupplierType() {
        return supplierType;
    }

    /**
     * @param supplierType
     */
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
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
}