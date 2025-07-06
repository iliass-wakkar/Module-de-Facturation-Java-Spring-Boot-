package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.controllers;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Facture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FactureController {
    @Autowired
    private FactureService factureService;

    @PostMapping("/facture")
    public ResponseEntity<?> creerFacture(@RequestBody Facture facture){
        try{
            if(facture == null)
                return ResponseEntity.badRequest().body("message : facture est vide");
            Facture neveauxFacture =factureService.creerFacture(facture);
            return ResponseEntity.ok().body(neveauxFacture);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
