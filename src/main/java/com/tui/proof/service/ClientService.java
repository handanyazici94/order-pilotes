package com.tui.proof.service;

import com.tui.proof.dto.ClientResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.dto.ClientDto;
import com.tui.proof.model.entity.Client;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientResponse register (ClientDto clientDto) throws ApiException {

        Optional<Client> client = clientRepository.findByEmail(clientDto.getEmail());
        if (client.isPresent()) {
            throw new ApiException("User already exists");
        }
        Client newClient = ClientMapper.mappingFromClientDtoToClient(clientDto);
        newClient = clientRepository.save(newClient);

        return ClientMapper.mappingFromClientToClientResponse(newClient);
    }

    public Client findClientById (Long clientId) throws ApiException {
        return clientRepository.findById(clientId).orElseThrow(()-> new ApiException("Client is not found"));
    }

    public Client findClientOfTheOrderExists(Long clientId, Long orderId) throws ApiException{
        return clientRepository.findByIdAndOrdersId(clientId, orderId)
            .orElseThrow(()-> new ApiException("Client or order is not found"));
    }

}
