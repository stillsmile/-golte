package com.golte.mapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "g_city_entry")
public class GCityEntry implements Serializable {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CENTRAL_CITY_ID")
    private Long centralCityId;

    @Column(name = "CITY_ID")
    private Long cityId;

    @Column(name = "ENTER_PERSON")
    private Long enterPerson;

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
     * @return ENTER_PERSON
     */
    public Long getEnterPerson() {
        return enterPerson;
    }

    /**
     * @param enterPerson
     */
    public void setEnterPerson(Long enterPerson) {
        this.enterPerson = enterPerson;
    }
}