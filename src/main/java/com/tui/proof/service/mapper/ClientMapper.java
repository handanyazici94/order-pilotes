package com.tui.proof.service.mapper;

import com.tui.proof.dto.ClientRequest;
import com.tui.proof.dto.ClientResponse;
import com.tui.proof.model.entity.Client;

public class ClientMapper {

    public static Client mappingFromClientDtoToClient(ClientRequest clientRequest) {
        Client client = new Client();

        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setTelephone(clientRequest.getTelephone());
        client.setEmail(clientRequest.getEmail());

        return client;
    }

    public static ClientResponse mappingFromClientToClientResponse(Client client) {
        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setId(client.getId());
        clientResponse.setFirstName(client.getFirstName());
        clientResponse.setLastName(client.getLastName());
        clientResponse.setTelephone(client.getTelephone());
        clientResponse.setEmail(client.getEmail());

        return clientResponse;
    }
}
