package com.avirantEnterprises.courseenrollment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GovtDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String date;
    private String panCardFilePath;
    private String aadharCardFilePath;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPanCardFilePath() {
        return panCardFilePath;
    }

    public void setPanCardFilePath(String panCardFilePath) {
        this.panCardFilePath = panCardFilePath;
    }

    public String getAadharCardFilePath() {
        return aadharCardFilePath;
    }

    public void setAadharCardFilePath(String aadharCardFilePath) {
        this.aadharCardFilePath = aadharCardFilePath;
    }
}

