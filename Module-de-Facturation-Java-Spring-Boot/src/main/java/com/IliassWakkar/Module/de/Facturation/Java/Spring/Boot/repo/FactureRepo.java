package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepo extends JpaRepository<Facture,Long> {
}
