package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_message_push")
public class GMessagePush implements Serializable {
    @Id
    @Column(name = "ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MES_TITLE")
    private String mesTitle;

    @Column(name = "MES_CONTENT")
    private String mesContent;

    @Column(name = "MES_RECEIVE_EMP_ID")
    private Long mesReceiveEmpId;

    @Column(name = "MES_STATUS")
    private String mesStatus;

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
     * @return MES_TITLE
     */
    public String getMesTitle() {
        return mesTitle;
    }

    /**
     * @param mesTitle
     */
    public void setMesTitle(String mesTitle) {
        this.mesTitle = mesTitle;
    }

    /**
     * @return MES_CONTENT
     */
    public String getMesContent() {
        return mesContent;
    }

    /**
     * @param mesContent
     */
    public void setMesContent(String mesContent) {
        this.mesContent = mesContent;
    }

    /**
     * @return MES_RECEIVE_EMP_ID
     */
    public Long getMesReceiveEmpId() {
        return mesReceiveEmpId;
    }

    /**
     * @param mesReceiveEmpId
     */
    public void setMesReceiveEmpId(Long mesReceiveEmpId) {
        this.mesReceiveEmpId = mesReceiveEmpId;
    }

    /**
     * @return MES_STATUS
     */
    public String getMesStatus() {
        return mesStatus;
    }

    /**
     * @param mesStatus
     */
    public void setMesStatus(String mesStatus) {
        this.mesStatus = mesStatus;
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