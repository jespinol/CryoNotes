package com.jmel.cryonotes.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String date;

    @Column
    @NotBlank
    private String sampleName;

    @Column
    @NotBlank
    private String sampleCategory;

    @Column
    @NotBlank
    private String creator;

    @Column
    private int molecularWeight;

    @Column
    private String isComplex;

    @Column
    private String stoichiometry;

    @Column
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleCategory() {
        return sampleCategory;
    }

    public void setSampleCategory(String sampleCategory) {
        this.sampleCategory = sampleCategory;
    }

    public int getMolecularWeight() {
        return molecularWeight;
    }

    public void setMolecularWeight(int molecularWeight) {
        this.molecularWeight = molecularWeight;
    }

    public String getIsComplex() {
        return isComplex;
    }

    public void setIsComplex(String isComplex) {
        this.isComplex = isComplex;
    }

    public String getStoichiometry() {
        return stoichiometry;
    }

    public void setStoichiometry(String stoichiometry) {
        this.stoichiometry = stoichiometry;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
