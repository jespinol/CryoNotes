package com.jmel.cryonotes.models;

import javax.persistence.*;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sampleName;

    @Column
    private String sampleCategory;

    @Column
    private String molecularWeight;

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

    public String getMolecularWeight() {
        return molecularWeight;
    }

    public void setMolecularWeight(String molecularWeight) {
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
}
