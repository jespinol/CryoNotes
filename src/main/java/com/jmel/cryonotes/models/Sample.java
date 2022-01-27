package com.jmel.cryonotes.models;

import javax.persistence.*;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
