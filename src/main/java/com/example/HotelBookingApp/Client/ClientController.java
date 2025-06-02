package com.example.HotelBookingApp.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody() ClientEntity client){
        try{
            ClientEntity createdClient = this.clientService.addNewClient(client);
            return ResponseEntity.ok("Client added successfully!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<ClientEntity> getAllClients(){
        return this.clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("id") int id){
        try{
            return ResponseEntity.ok(this.clientService.getClientById(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") int id){
        try {
            this.clientService.deleteClientById(id);
            return ResponseEntity.ok("Client deleted successfully!");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClientById(@PathVariable("id") int id,@RequestBody() ClientEntity updatedClient){
        try{
            ClientEntity client = this.clientService.updateClientById(id,updatedClient);
            return ResponseEntity.ok("Client updated successfully!");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
    }

}
