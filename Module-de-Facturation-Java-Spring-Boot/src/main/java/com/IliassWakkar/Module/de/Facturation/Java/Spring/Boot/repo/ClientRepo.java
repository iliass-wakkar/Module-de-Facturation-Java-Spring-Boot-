package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {
}
