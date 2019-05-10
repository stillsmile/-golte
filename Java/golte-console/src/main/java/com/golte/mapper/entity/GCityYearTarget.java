package com.golte.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "g_city_year_target")
public class GCityYearTarget implements Serializable {
    @Id
    @Column(name = "ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY_ID")
    private Long cityId;

    @Column(name = "YEAR")
    private String year;

    @Column(name = "YEAR_TARGET")
    private BigDecimal yearTarget;

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
     * @return CITY_ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * @return YEAR
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return YEAR_TARGET
     */
    public BigDecimal getYearTarget() {
        return yearTarget;
    }

    /**
     * @param yearTarget
     */
    public void setYearTarget(BigDecimal yearTarget) {
        this.yearTarget = yearTarget;
    }
}