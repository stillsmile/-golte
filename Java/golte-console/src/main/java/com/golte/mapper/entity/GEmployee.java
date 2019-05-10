package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_employee")
public class GEmployee implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "EMP_ACCOUNT_NUMBER")
    private String empAccountNumber;

    @Column(name = "EMP_PASSWORD")
    private String empPassword;

    @Column(name = "EMP_EMAIL")
    private String empEmail;

    @Column(name = "EMP_JOB_TITLE")
    private String empJobTitle;

    @Column(name = "EMP_SORT_VALUE")
    private Integer empSortValue;

    @Column(name = "EMP_STATUS")
    private String empStatus;

    @Column(name = "CREATE_NAME")
    private String createName;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_NAME")
    private String updateName;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "DEL_STATUS")
    private String delStatus;

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
     * @return EMP_NAME
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return EMP_ACCOUNT_NUMBER
     */
    public String getEmpAccountNumber() {
        return empAccountNumber;
    }

    /**
     * @param empAccountNumber
     */
    public void setEmpAccountNumber(String empAccountNumber) {
        this.empAccountNumber = empAccountNumber;
    }

    /**
     * @return EMP_PASSWORD
     */
    public String getEmpPassword() {
        return empPassword;
    }

    /**
     * @param empPassword
     */
    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    /**
     * @return EMP_EMAIL
     */
    public String getEmpEmail() {
        return empEmail;
    }

    /**
     * @param empEmail
     */
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    /**
     * @return EMP_JOB_TITLE
     */
    public String getEmpJobTitle() {
        return empJobTitle;
    }

    /**
     * @param empJobTitle
     */
    public void setEmpJobTitle(String empJobTitle) {
        this.empJobTitle = empJobTitle;
    }

    /**
     * @return EMP_SORT_VALUE
     */
    public Integer getEmpSortValue() {
        return empSortValue;
    }

    /**
     * @param empSortValue
     */
    public void setEmpSortValue(Integer empSortValue) {
        this.empSortValue = empSortValue;
    }

    /**
     * @return EMP_STATUS
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     * @param empStatus
     */
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
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
}