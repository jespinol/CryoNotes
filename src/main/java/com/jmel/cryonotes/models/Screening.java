package com.jmel.cryonotes.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "screening")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String date;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String category;

    @Column
    @NotBlank
    private String creator;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

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
