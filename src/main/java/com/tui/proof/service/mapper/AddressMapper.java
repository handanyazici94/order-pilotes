package com.tui.proof.service.mapper;

import com.tui.proof.dto.AddressRequest;
import com.tui.proof.model.entity.Address;

public class AddressMapper {

    public static Address mappingAddressDtoToAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setStreet(addressRequest.getStreet());
        address.setPostcode(addressRequest.getPostcode());

        return address;
    }
    public static Address mappingFromAddressDtoToExistAddress(AddressRequest addressRequest, Address address) {
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setStreet(addressRequest.getStreet());
        address.setPostcode(addressRequest.getPostcode());

        return address;
    }

}
