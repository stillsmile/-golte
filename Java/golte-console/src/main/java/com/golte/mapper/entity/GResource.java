package com.golte.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "g_resource")
public class GResource implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "RES_NAME")
    private String resName;

    /**
     * url
     */
    @Column(name = "RES_URL")
    private String resUrl;

    /**
     * 资源类型 1模块 2页面 3按钮	
     */
    @Column(name = "RES_TYPE")
    private String resType;

    /**
     * 父节点
     */
    @Column(name = "RES_PARTEN_ID")
    private Long resPartenId;

    /**
     * 图标
     */
    @Column(name = "RES_ICON")
    private String resIcon;

    /**
     * 描述
     */
    @Column(name = "RES_DESCRIPTION")
    private String resDescription;

    /**
     * 状态 0无效 1有效
     */
    @Column(name = "RES_STATUS")
    private String resStatus;

    /**
     * 排序
     */
    @Column(name = "RES_SEQ")
    private Integer resSeq;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CREATE_NAME")
    private String createName;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_NAME")
    private String updateName;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id
     *
     * @return ID - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return RES_NAME - 名称
     */
    public String getResName() {
        return resName;
    }

    /**
     * 设置名称
     *
     * @param resName 名称
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * 获取url
     *
     * @return RES_URL - url
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 设置url
     *
     * @param resUrl url
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 获取资源类型 1模块 2页面 3按钮	
     *
     * @return RES_TYPE - 资源类型 1模块 2页面 3按钮	
     */
    public String getResType() {
        return resType;
    }

    /**
     * 设置资源类型 1模块 2页面 3按钮	
     *
     * @param resType 资源类型 1模块 2页面 3按钮	
     */
    public void setResType(String resType) {
        this.resType = resType;
    }

    /**
     * 获取父节点
     *
     * @return RES_PARTEN_ID - 父节点
     */
    public Long getResPartenId() {
        return resPartenId;
    }

    /**
     * 设置父节点
     *
     * @param resPartenId 父节点
     */
    public void setResPartenId(Long resPartenId) {
        this.resPartenId = resPartenId;
    }

    /**
     * 获取图标
     *
     * @return RES_ICON - 图标
     */
    public String getResIcon() {
        return resIcon;
    }

    /**
     * 设置图标
     *
     * @param resIcon 图标
     */
    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    /**
     * 获取描述
     *
     * @return RES_DESCRIPTION - 描述
     */
    public String getResDescription() {
        return resDescription;
    }

    /**
     * 设置描述
     *
     * @param resDescription 描述
     */
    public void setResDescription(String resDescription) {
        this.resDescription = resDescription;
    }

    /**
     * 获取状态 0无效 1有效
     *
     * @return RES_STATUS - 状态 0无效 1有效
     */
    public String getResStatus() {
        return resStatus;
    }

    /**
     * 设置状态 0无效 1有效
     *
     * @param resStatus 状态 0无效 1有效
     */
    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    /**
     * 获取排序
     *
     * @return RES_SEQ - 排序
     */
    public Integer getResSeq() {
        return resSeq;
    }

    /**
     * 设置排序
     *
     * @param resSeq 排序
     */
    public void setResSeq(Integer resSeq) {
        this.resSeq = resSeq;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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
}