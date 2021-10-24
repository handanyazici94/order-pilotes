package com.tui.proof.service;

import com.tui.proof.dto.ClientRequest;
import com.tui.proof.dto.ClientResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.model.entity.Client;
import com.tui.proof.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientServiceTest {
    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    private Client client;
    private ClientRequest clientRequest;
    private final Long clientId = 1L;
    private final Long orderId = 1L;

    @BeforeEach
    void createClient() {
        client = new Client();
        client.setId(clientId);
        client.setTelephone("05323452347");
        client.setEmail("hyayla@gmail.com");
        client.setFirstName("Handan");
        client.setLastName("Yayla");

        clientRequest = new ClientRequest();
        clientRequest.setTelephone("05323452347");
        clientRequest.setEmail("hyayla@gmail.com");
        clientRequest.setFirstName("Handan");
        clientRequest.setLastName("Yayla");
    }

    @AfterEach
    void deleteClient() {
        System.out.println("delete Client...");
    }

    @Test
    @DisplayName("add_existClientDto_returnUserAlreadyExistsException")
    public void add_existClientDto_returnClientResponse() {
        when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.of(client));

        ApiException actualException = assertThrows(ApiException.class, () -> {
            clientService.add(clientRequest);
        });

        assertEquals("User already exists", actualException.getMessage());
    }

    @Test
    @DisplayName("add_notExistClientDto_returnClientResponse")
    public void add_notExistClientDto_returnClientResponse() throws ApiException {
        when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientResponse actualClientResponse = clientService.add(clientRequest);

        assertEquals(client.getId(), actualClientResponse.getId());
        assertEquals(client.getEmail(), actualClientResponse.getEmail());
        assertEquals(client.getFirstName(), actualClientResponse.getFirstName());
        assertEquals(client.getLastName(), actualClientResponse.getLastName());
        assertEquals(client.getTelephone(), actualClientResponse.getTelephone());
    }

    @Test
    @DisplayName("findClientById_validClientId_returnClient")
    public void findClientById_validClientId_returnClient() throws ApiException {
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));

        Client actualClient = clientService.findClientById(clientId);

        assertSame(client, actualClient);
    }

    @Test
    @DisplayName("findClientById_inValidClientId_returnClientIsNotFoundException")
    public void findClientById_inValidClientId_returnClientIsNotFoundException() {
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ApiException actualException = assertThrows(ApiException.class, () -> {
            clientService.findClientById(clientId);
        });

        assertEquals("Client couldn't be found", actualException.getMessage());
    }
}
