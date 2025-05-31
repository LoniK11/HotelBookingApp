package com.example.HotelBookingApp.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody() ClientEntity client){
        return this.clientService.addNewClient(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients(){
        return this.clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("id") int id){
        return this.clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") int id){
        return this.clientService.deleteClientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClientById(@PathVariable("id") int id,@RequestBody() ClientEntity client){
        return this.clientService.updateClientById(id,client);
    }

}
