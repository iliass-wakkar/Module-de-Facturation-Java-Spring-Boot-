package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.services;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Facture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo.FactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureService {
    @Autowired
    private FactureRepo factureRepo;

    public Facture creerFacture(Facture facture) {
        try {
            return factureRepo.save(facture);
        }catch (Exception e){
            throw new RuntimeException("Erreur de creation de Facture");
        }
    }
}
