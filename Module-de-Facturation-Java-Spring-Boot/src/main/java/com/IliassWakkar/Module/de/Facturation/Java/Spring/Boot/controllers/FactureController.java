package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.controllers;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.factureDto.FactureRequestDto;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.factureDto.FactureRespenseDto;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Facture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.LigneFacture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FactureController {
    @Autowired
    private FactureService factureService;

    @PostMapping("/facture")
    public ResponseEntity<?> creerFacture(@RequestBody FactureRequestDto facture){
        try{
            if(facture == null)
                return ResponseEntity.badRequest().body("message : facture est vide");
            Facture neveauxFacture =factureService.creerFacture(facture);
            return ResponseEntity.ok().body(neveauxFacture);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/factures")
    public ResponseEntity<?> getFactures(){
        try{
            List<Facture> listerFactures = factureService.listerFactures();
            return ResponseEntity.ok().body(FactureRespenseDto.fullFactureList(listerFactures));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
