package com.example.oblig3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Kinobillett {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String filmnavn;
    private int antall;
    private String fornavn;
    private String etternavn;
    private String telefon;
    private String epost;
}