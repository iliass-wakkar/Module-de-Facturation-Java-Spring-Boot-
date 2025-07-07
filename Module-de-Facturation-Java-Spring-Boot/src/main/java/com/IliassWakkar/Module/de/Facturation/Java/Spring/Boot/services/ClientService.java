package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.services;

import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.Dtos.clientDto.ClientRequestDTO;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Client;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.repo.ClientRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Client creerClient(ClientRequestDTO requestDTO) throws Exception{
        try {
            Client client = new Client();
            client.setNom(requestDTO.nom());
            client.setEmail(requestDTO.email());
            client.setSiret(requestDTO.siret());
            client.setDateDeCreation(requestDTO.dateDeCreation());
            return clientRepo.save(client);
        }catch (Exception e){
            throw new Exception("Erreur lors de la souvgarde de Client");
        }
    }

    public List<Client> listerClients() throws Exception{
        try {
            return clientRepo.findAll();
        }catch (Exception e){
            throw new Exception("Erreur lors de recuperation des clients");
        }
    }

    public Client clientDetails(Long id) throws Exception{
        try {
            return clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Client"));
        }catch (Exception e){
            throw new Exception("Erreur lors de recuperation des client");
        }
    }

}
