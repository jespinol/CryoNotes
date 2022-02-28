package com.jmel.cryonotes.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "screening")
public class Screening extends AbstractEntity {

    @ManyToOne
    @NotNull
    private Sample sample;

    @ManyToOne
    @NotNull
    private Microscope microscope;

    @NotNull
    private String grid;

    @Column
    private String result;

    @Column
    @NotBlank
    private String wasCollected;

    @Column
    private String wasStored;

    @Column
    private String comments;

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Microscope getMicroscope() {
        return microscope;
    }

    public void setMicroscope(Microscope microscope) {
        this.microscope = microscope;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWasCollected() {
        return wasCollected;
    }

    public void setWasCollected(String wasCollected) {
        this.wasCollected = wasCollected;
    }

    public String getWasStored() {
        return wasStored;
    }

    public void setWasStored(String wasStored) {
        this.wasStored = wasStored;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
