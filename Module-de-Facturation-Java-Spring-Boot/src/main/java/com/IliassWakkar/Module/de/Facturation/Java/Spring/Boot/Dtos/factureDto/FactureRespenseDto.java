package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.factureDto;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Client;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Facture;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.LigneFacture;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record FactureRespenseDto(
        @NotNull
        LocalDate Date,
        @NotNull
        Client client,

        @Size(min = 1)
        @NotNull
        List<LigneFacture> ligneFactures,

        Double totalHT,
        Double totalTVA,
        Double TotalTTC

) {
    public static  List<FactureRespenseDto> fullFactureList(List<Facture> factures) {
        return factures.stream()
                .map(FactureRespenseDto::fullFacture)
                .collect(Collectors.toList());
    }
    public static  FactureRespenseDto fullFacture(Facture facture) {

        Double totalHT = 0.0;
        Double totalTVA = 0.0;
        for(LigneFacture ligne : facture.getLigneFactures()){
            double ht = ligne.getPrixUnitaireHT() * ligne.getQuantite();
            double tva = ht * (ligne.getTauxDeTVA() / 100);

            totalHT += ht;
            totalTVA += tva;
        }
        Double totalTTC = totalHT + totalTVA;
        return new FactureRespenseDto(
                facture.getDate(),
                facture.getClient(),
                facture.getLigneFactures(),
                totalHT,
                totalTVA,
                totalTTC
        );
    }
}
