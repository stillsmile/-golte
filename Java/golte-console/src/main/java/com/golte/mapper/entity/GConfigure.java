package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_configure")
public class GConfigure implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COG_KEY")
    private String cogKey;

    @Column(name = "COG_VALUE")
    private String cogValue;

    @Column(name = "COG_NAME")
    private String cogName;

    @Column(name = "COG_EXTEND_ONE")
    private String cogExtendOne;

    @Column(name = "COG_EXTEND_TWO")
    private String cogExtendTwo;

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
     * @return COG_KEY
     */
    public String getCogKey() {
        return cogKey;
    }

    /**
     * @param cogKey
     */
    public void setCogKey(String cogKey) {
        this.cogKey = cogKey;
    }

    /**
     * @return COG_VALUE
     */
    public String getCogValue() {
        return cogValue;
    }

    /**
     * @param cogValue
     */
    public void setCogValue(String cogValue) {
        this.cogValue = cogValue;
    }

    /**
     * @return COG_NAME
     */
    public String getCogName() {
        return cogName;
    }

    /**
     * @param cogName
     */
    public void setCogName(String cogName) {
        this.cogName = cogName;
    }

    /**
     * @return COG_EXTEND_ONE
     */
    public String getCogExtendOne() {
        return cogExtendOne;
    }

    /**
     * @param cogExtendOne
     */
    public void setCogExtendOne(String cogExtendOne) {
        this.cogExtendOne = cogExtendOne;
    }

    /**
     * @return COG_EXTEND_TWO
     */
    public String getCogExtendTwo() {
        return cogExtendTwo;
    }

    /**
     * @param cogExtendTwo
     */
    public void setCogExtendTwo(String cogExtendTwo) {
        this.cogExtendTwo = cogExtendTwo;
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