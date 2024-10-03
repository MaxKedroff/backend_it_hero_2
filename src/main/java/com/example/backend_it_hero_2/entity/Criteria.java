package com.example.backend_it_hero_2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "criteria")
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long criteriaId;

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaDescription() {
        return criteriaDescription;
    }

    public void setCriteriaDescription(String criteriaDescription) {
        this.criteriaDescription = criteriaDescription;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(name = "criteria_name", nullable = false)
    private String criteriaName;

    @Column(name = "criteria_description")
    private String criteriaDescription;

    private Integer rating;
}
