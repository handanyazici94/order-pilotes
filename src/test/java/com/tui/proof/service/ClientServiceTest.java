package com.tui.proof.service;

import com.tui.proof.dto.ClientDto;
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
    private ClientDto clientDto;
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

        clientDto = new ClientDto();
        clientDto.setTelephone("05323452347");
        clientDto.setEmail("hyayla@gmail.com");
        clientDto.setFirstName("Handan");
        clientDto.setLastName("Yayla");
    }

    @AfterEach
    void deleteClient() {
        System.out.println("delete Client...");
    }

    @Test
    @DisplayName("register_existClientDto_returnUserAlreadyExistsException")
    public void register_existClientDto_returnClientResponse() {
        when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.of(client));

        ApiException actualException = assertThrows(ApiException.class, () -> {
            clientService.register(clientDto);
        });

        assertEquals("User already exists", actualException.getMessage());
    }

    @Test
    @DisplayName("register_notExistClientDto_returnClientResponse")
    public void register_notExistClientDto_returnClientResponse() throws ApiException {
        when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientResponse actualClientResponse = clientService.register(clientDto);

        assertEquals(client.getId(), actualClientResponse.getId());
        assertEquals(client.getEmail(), actualClientResponse.getEmail());
        assertEquals(client.getFirstName(), actualClientResponse.getFirstName());
        assertEquals(client.getLastName(), actualClientResponse.getLastName());
        assertEquals(client.getTelephone(), actualClientResponse.getTelephone());
    }

/*
    @Test
    @DisplayName("checkClientByEmail_existEmail_returnUserAlreadyExistsException")
    public void checkClientByEmail_existEmail_returnUserAlreadyExistsException() {
        when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.of(client));

        ApiException exception = assertThrows(ApiException.class, () -> {
            clientService.checkClientByEmail(clientDto.getEmail());
        });

        assertEquals(exception.getMessage(), "User already exists");
    }
*/
    @Test
    @DisplayName("findClientOfTheOrderExists_validClientIdAndOrderId_returnClient")
    public void findClientOfTheOrderExists_validClientIdAndOrderId_returnClient() throws ApiException {
        when(clientRepository.findByIdAndOrdersId(any(Long.class),any(Long.class))).thenReturn(Optional.of(client));

        Client actualClient = clientService.findClientOfTheOrderExists(clientId, orderId);

        assertSame(client, actualClient);
    }

    @Test
    @DisplayName("findClientOfTheOrderExists_validClientIdAndInvalidOrderId_returnClientOrOrderIsNotFoundException")
    public void findClientOfTheOrderExists_validClientIdAndInvalidOrderId_returnClientOrOrderIsNotFoundException() {
        when(clientRepository.findByIdAndOrdersId(any(Long.class),any(Long.class))).thenReturn(Optional.empty());

        ApiException actualException = assertThrows(ApiException.class, () -> {
            clientService.findClientOfTheOrderExists(clientId, orderId);
        });

        assertEquals("Client or order is not found", actualException.getMessage());
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

        assertEquals("Client is not found", actualException.getMessage());
    }
}
