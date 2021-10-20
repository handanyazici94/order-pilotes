package com.tui.proof.service.mapper;

import com.tui.proof.dto.AddressDto;
import com.tui.proof.model.entity.Address;

public class AddressMapper {

    public static Address mappingAddressDtoToAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        address.setPostcode(addressDto.getPostcode());

        return address;
    }
    public static Address mappingFromAddressDtoToExistAddress(AddressDto addressDto, Address address) {
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        address.setPostcode(addressDto.getPostcode());

        return address;
    }

}
