package com.golte.mapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "g_merchant_evaluation")
public class GMerchantEvaluation implements Serializable {
    @Id
    @Column(name = "ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MERCHANT_ID")
    private Long merchantId;

    @Column(name = "COOPERATION_EVALUATION")
    private String cooperationEvaluation;

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
     * @return MERCHANT_ID
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return COOPERATION_EVALUATION
     */
    public String getCooperationEvaluation() {
        return cooperationEvaluation;
    }

    /**
     * @param cooperationEvaluation
     */
    public void setCooperationEvaluation(String cooperationEvaluation) {
        this.cooperationEvaluation = cooperationEvaluation;
    }
}