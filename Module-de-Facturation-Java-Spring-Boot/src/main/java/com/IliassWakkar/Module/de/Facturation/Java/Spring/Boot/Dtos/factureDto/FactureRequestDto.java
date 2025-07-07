package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.factureDto;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.LigneFacture;

import java.time.LocalDate;
import java.util.List;

public record FactureRequestDto(
        Long client_id,
        LocalDate date,
        List<LigneFacture> ligneFactures
) {}

