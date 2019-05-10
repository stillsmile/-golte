package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_annex")
public class GAnnex implements Serializable {
    @Column(name = "TYPE")
    private String type;

    @Column(name = "ASSOCIATION_ID")
    private Long associationId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    private static final long serialVersionUID = 1L;

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
     * @return ASSOCIATION_ID
     */
    public Long getAssociationId() {
        return associationId;
    }

    /**
     * @param associationId
     */
    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
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
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
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