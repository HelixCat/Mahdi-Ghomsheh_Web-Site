package com.mahdi.food_ordering.model;


import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "c_active")
    private Boolean active;
    @Column(name = "c_manualId")
    private String manualId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getManualId() {
        return manualId;
    }

    public void setManualId(String manualId) {
        this.manualId = manualId;
    }
}
