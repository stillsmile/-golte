package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_exception")
public class GException implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXCEPTION_URL")
    private String exceptionUrl;

    @Column(name = "EXCEPTION_CONTENT")
    private String exceptionContent;

    @Column(name = "CREATE_USER")
    private String createUser;

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
     * @return EXCEPTION_URL
     */
    public String getExceptionUrl() {
        return exceptionUrl;
    }

    /**
     * @param exceptionUrl
     */
    public void setExceptionUrl(String exceptionUrl) {
        this.exceptionUrl = exceptionUrl;
    }

    /**
     * @return EXCEPTION_CONTENT
     */
    public String getExceptionContent() {
        return exceptionContent;
    }

    /**
     * @param exceptionContent
     */
    public void setExceptionContent(String exceptionContent) {
        this.exceptionContent = exceptionContent;
    }

    /**
     * @return CREATE_USER
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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