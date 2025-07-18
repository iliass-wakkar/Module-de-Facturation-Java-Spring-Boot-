package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class LigneFacture {
    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private Integer quantite;

    @NotNull
    private Double prixUnitaireHT;

    @NotNull
    private Double tauxDeTVA;

    //chaque ligne de facture a une seul factur
    @ManyToOne
    @JoinColumn(name = "facture_id")
    @JsonBackReference
    private Facture facture;

    //constricteurs
    public LigneFacture(Long id, String description, Integer quantite, Double prixUnitaireHT, Double tauxDeTVA, Facture facture) {
        this.id = id;
        this.description = description;
        this.quantite = quantite;
        this.prixUnitaireHT = prixUnitaireHT;
        this.tauxDeTVA = tauxDeTVA;
        this.facture = facture;
    }

    public LigneFacture() {
    }

    //getters et setters


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantité) {
        this.quantite = quantité;
    }

    public Double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    public void setPrixUnitaireHT(Double prixUnitaireHT) {
        this.prixUnitaireHT = prixUnitaireHT;
    }

    public Double getTauxDeTVA() {
        return tauxDeTVA;
    }

    public void setTauxDeTVA(Double tauxDeTVA) {
        this.tauxDeTVA = tauxDeTVA;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
}

