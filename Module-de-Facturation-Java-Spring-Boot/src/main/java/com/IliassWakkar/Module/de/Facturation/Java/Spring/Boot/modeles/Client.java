package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;


//etity client
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String email;

    @NotBlank
    private String siret;

    @NotNull
    private LocalDate dateDeCreation;

    //chaque client a plusier facture
    @JsonIgnore
    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL)
    private List<Facture> factures;

    //constricteur
    public Client(Long id, String nom, String email, String siret, LocalDate dateDeCreation, List<Facture> factures) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.siret = siret;
        this.dateDeCreation = dateDeCreation;
        this.factures = factures;
    }

    public Client() {

    }

    //getters et setters


    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCréation) {
        this.dateDeCreation = dateDeCréation;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
}
