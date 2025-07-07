package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.clientDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientRequestDTO (

    @NotBlank
    String nom,

    @NotBlank
    String email,

    @NotBlank
    String siret,

    @NotNull
    LocalDate dateDeCreation){

}
