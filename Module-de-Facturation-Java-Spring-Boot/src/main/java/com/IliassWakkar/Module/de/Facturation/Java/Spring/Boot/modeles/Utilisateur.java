package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//entity utilisateur pour l'authentification
@Entity
public class Utilisateur {
    @Id @GeneratedValue
    private Long id;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String motDePasse;

    //constricteur
    public Utilisateur(Long id, String email, String motDePasse) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Utilisateur() {
    }

    // getter sitter

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
