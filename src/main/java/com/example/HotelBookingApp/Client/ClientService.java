package com.example.HotelBookingApp.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientEntity addNewClient(ClientEntity client){
        if(client == null){
            throw new IllegalArgumentException("Client cannot be null!");
        }

        return this.clientRepository.save(client);
    }

    public List<ClientEntity> getAllClients(){
        return this.clientRepository.findAll();
    }

    public ClientEntity getClientById(int id){
        return this.clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No client found!"));
    }

    public void deleteClientById(int id){
        if(!this.clientRepository.existsById(id)){
            throw new NoSuchElementException("No client found!");
        }
        this.clientRepository.deleteById(id);
    }

    public ClientEntity updateClientById(int id, ClientEntity updatedClient) {
        ClientEntity client = this.clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No client found!"));

        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setEmail(updatedClient.getEmail());
        client.setPhone(updatedClient.getPhone());

        return this.clientRepository.save(client);
    }
}
