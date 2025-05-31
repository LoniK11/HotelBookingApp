package com.example.HotelBookingApp.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<ClientEntity> getClientById(int id){
        Optional<ClientEntity> client = this.clientRepository.findById(id);

        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteClientById(int id){
        Optional<ClientEntity> client = this.clientRepository.findById(id);

        if(client.isPresent()){
            this.clientRepository.deleteById(id);
            return ResponseEntity.ok("Client deleted successfully!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find client!");
        }
    }

    public ResponseEntity<String> updateClientById(int id, ClientEntity updatedClient){
        Optional<ClientEntity> existingClient = this.clientRepository.findById(id);

        if(existingClient.isPresent()){
            ClientEntity client = existingClient.get();

            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());
            client.setEmail(updatedClient.getEmail());
            client.setPhone(updatedClient.getPhone());

            this.clientRepository.save(client);

            return ResponseEntity.ok("Client updated successfully!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find client!");
        }
    }
}
