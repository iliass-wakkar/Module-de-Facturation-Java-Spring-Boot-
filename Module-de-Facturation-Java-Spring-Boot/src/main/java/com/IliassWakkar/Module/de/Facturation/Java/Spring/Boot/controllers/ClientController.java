package com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.controllers;


import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.modeles.Client;
import com.IliassWakkar.Module.de.Facturation.Java.Spring.Boot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //pour cree client endpoint http://localhost:8080/api/client
    @PostMapping("/client")
    public ResponseEntity<?> creeClient(@RequestBody Client client){
        try{
            if(client == null) return ResponseEntity.badRequest().body("massage: vide client");
            Client clientSouvgarder = clientService.creerClient(client);
            return ResponseEntity.ok().body(clientSouvgarder);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("clients")
    public ResponseEntity<?> listClients(){
        try{
            List<Client> clientsList = clientService.listerClients();
            return ResponseEntity.ok().body(clientsList);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> listClients( @PathVariable Long id){
        try{
            Client clientsList = clientService.clientDetails(id);
            return ResponseEntity.ok().body(clientsList);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
