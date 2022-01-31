package com.jmel.cryonotes.models;

import javax.persistence.*;

@Entity
@Table(name = "screening")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sampleName;

    @Column
    private String sampleCategory;

    @Column
    private String microscope;

    @Column
    private String vitrification;

    @Column
    private String pixelSize;

    @Column
    private String result;

    @Column
    private String collected;

    @Column
    private String collectionTime;

    @Column
    private String numMicrographs;

    @Column
    private String storage;

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

    public String getMicroscope() {
        return microscope;
    }

    public void setMicroscope(String microscope) {
        this.microscope = microscope;
    }

    public String getVitrification() {
        return vitrification;
    }

    public void setVitrification(String vitrification) {
        this.vitrification = vitrification;
    }

    public String getPixelSize() {
        return pixelSize;
    }

    public void setPixelSize(String pixelSize) {
        this.pixelSize = pixelSize;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getNumMicrographs() {
        return numMicrographs;
    }

    public void setNumMicrographs(String numMicrographs) {
        this.numMicrographs = numMicrographs;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
