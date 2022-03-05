package com.jmel.cryonotes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grids")
public class Grid extends AbstractEntity{
    @ManyToOne
    private Sample sample;

    @OneToMany(mappedBy = "grid")
    private final List<Screening> screenings = new ArrayList<>();

    private double concentration;

    private String buffer;

    private String salt;

    private String additives;

    private String sampleComments;

    private String gridMaterial;

    private String gridSize;

    private String glowDischarge;

    private String vitrificationComments;

    private String storageComments;

    private String comments;

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAdditives() {
        return additives;
    }

    public void setAdditives(String additives) {
        this.additives = additives;
    }

    public String getSampleComments() {
        return sampleComments;
    }

    public void setSampleComments(String sampleComments) {
        this.sampleComments = sampleComments;
    }

    public String getGridMaterial() {
        return gridMaterial;
    }

    public void setGridMaterial(String gridMaterial) {
        this.gridMaterial = gridMaterial;
    }

    public String getGridSize() {
        return gridSize;
    }

    public void setGridSize(String gridSize) {
        this.gridSize = gridSize;
    }

    public String getGlowDischarge() {
        return glowDischarge;
    }

    public void setGlowDischarge(String glowDischarge) {
        this.glowDischarge = glowDischarge;
    }

    public String getVitrificationComments() {
        return vitrificationComments;
    }

    public void setVitrificationComments(String vitrificationComments) {
        this.vitrificationComments = vitrificationComments;
    }

    public String getStorageComments() {
        return storageComments;
    }

    public void setStorageComments(String storageComments) {
        this.storageComments = storageComments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
