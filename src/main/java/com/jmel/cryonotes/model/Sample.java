package com.jmel.cryonotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "samples")
public class Sample extends AbstractEntity {

    @Column
    private int molecularWeight;

    @Column
    private String isComplex;

    @Column
    private String stoichiometry;

    @Column
    private String comments;

    @OneToMany(mappedBy = "sample")
    private final List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "sample")
    private final List<Grid> grids = new ArrayList<>();

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

    public List<Screening> getScreenings() {
        return screenings;
    }

    public List<Grid> getGrids() {return grids;
    }
}
