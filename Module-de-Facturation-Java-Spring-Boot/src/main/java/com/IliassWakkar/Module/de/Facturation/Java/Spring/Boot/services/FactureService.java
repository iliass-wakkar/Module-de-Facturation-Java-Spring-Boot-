package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.services;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.factureDto.FactureRequestDto;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Client;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Facture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.LigneFacture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo.ClientRepo;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo.FactureRepo;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo.LigneFactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {
    @Autowired
    private FactureRepo factureRepo;
    @Autowired
    private ClientRepo clientRepo;
    public Facture creerFacture(FactureRequestDto facture) throws  Exception{
        try {
            Client client= clientRepo.findById(facture.client_id())
                    .orElseThrow( () ->  new Exception("Client n'existe pas"));
            if(client==null)
                throw new Exception("message : client n\"exists pas");
            Facture fact = new Facture();
            fact.setDate(facture.date());
            fact.setClient(client);
            List<LigneFacture> lignes = facture.ligneFactures();
            fact.setLigneFactures(lignes);
            return factureRepo.save(fact);
        }catch (Exception e){
            throw new Exception("messages : " + e.getMessage());
        }
    }

    public List<Facture> listerFactures() throws Exception{
        try {
            return factureRepo.findAll();
        }catch (Exception e){
            throw new Exception("messages : " + e.getMessage());
        }
    }
}
