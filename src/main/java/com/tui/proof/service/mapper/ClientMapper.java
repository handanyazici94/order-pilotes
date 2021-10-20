package com.tui.proof.service.mapper;

import com.tui.proof.dto.ClientDto;
import com.tui.proof.dto.ClientResponse;
import com.tui.proof.model.entity.Client;

public class ClientMapper {

    public static Client mappingFromClientDtoToClient(ClientDto clientDto) {
        Client client = new Client();

        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setTelephone(clientDto.getTelephone());
        client.setEmail(clientDto.getEmail());

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
