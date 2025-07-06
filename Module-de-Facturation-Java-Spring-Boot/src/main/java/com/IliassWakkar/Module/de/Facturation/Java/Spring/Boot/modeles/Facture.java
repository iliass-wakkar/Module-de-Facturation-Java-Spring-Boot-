package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles;


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
    @JoinColumn(name = "clientId")
    private Client client;

    @Size(min = 1)
    @NotNull
    @OneToMany(mappedBy = "facture" , cascade = CascadeType.ALL)
    private List<LigneFacture> ligneFactures;
}
