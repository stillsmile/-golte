package com.golte.mapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "g_contract_resource")
public class GContractResource implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "CONTRACT_PROJECT_ID")
    private Long contractProjectId;

    @Column(name = "RESOURCE_ID")
    private Long resourceId;

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
     * @return RESOURCE_ID
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}