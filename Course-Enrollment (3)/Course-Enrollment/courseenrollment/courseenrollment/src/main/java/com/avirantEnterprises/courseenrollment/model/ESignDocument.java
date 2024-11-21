package com.avirantEnterprises.courseenrollment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
public class ESignDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Document or signer's name, required

    @Column(name = "signing_date", nullable = false)
    private LocalDate date; // Better type for date handling, required

    @Column(name = "file_path", nullable = false)
    private String filePath; // Path to the file, required

    // No-argument constructor (mandatory for JPA)
    public ESignDocument() {
    }

    // Parameterized constructor
    public ESignDocument(String name, LocalDate date, String filePath) {
        this.name = name;
        this.date = date;
        this.filePath = filePath;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
