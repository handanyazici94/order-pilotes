package com.tui.proof.service;

import com.tui.proof.exception.ApiException;
import com.tui.proof.model.entity.Address;
import com.tui.proof.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address findAddressById (Long addressId) throws ApiException {
        return addressRepository.findById(addressId).orElseThrow(()-> new ApiException("Address is not found"));
    }

    public Address saveAddress (Address address) {
        return addressRepository.save(address);
    }
}
