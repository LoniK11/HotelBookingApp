package com.example.HotelBookingApp.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<String> addNewClient(ClientEntity client){
         this.clientRepository.save(client);
         return ResponseEntity.status(HttpStatus.CREATED).body("Client added successfully!");
    }

    public ResponseEntity<List<ClientEntity>> getAllClients(){
        return ResponseEntity.ok(this.clientRepository.findAll().stream().toList());
    }
}
