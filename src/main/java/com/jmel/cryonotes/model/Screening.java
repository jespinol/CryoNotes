package com.jmel.cryonotes.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "screening")
public class Screening extends AbstractEntity{

    @Column
    @NotBlank
    private String microscope;

    @Column
    @NotBlank
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

    public String getMicroscope() {
        return microscope;
    }

    public void setMicroscope(String microscope) {
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
