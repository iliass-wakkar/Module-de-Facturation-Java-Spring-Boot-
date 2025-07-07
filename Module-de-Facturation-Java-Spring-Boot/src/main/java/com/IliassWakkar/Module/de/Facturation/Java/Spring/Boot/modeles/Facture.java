package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Facture {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate Date;

    @ManyToOne
    @JoinColumn(name = "clien_id")
    private Client client;

    @Size(min = 1)
    @NotNull
    @OneToMany( cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LigneFacture> ligneFactures;

    //constricteurs
    public Facture(Long id, LocalDate date, Client client, List<LigneFacture> ligneFactures) {
        this.id = id;
        Date = date;
        this.client = client;
        this.ligneFactures = ligneFactures;
    }
    public Facture() {
    }

    //getters et sitters

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }
}
