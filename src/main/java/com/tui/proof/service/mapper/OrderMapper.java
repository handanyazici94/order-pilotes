package com.tui.proof.service.mapper;

import com.tui.proof.dto.*;
import com.tui.proof.model.entity.Address;
import com.tui.proof.model.entity.Client;
import com.tui.proof.model.entity.Order;
import com.tui.proof.model.rabbitMq.AddressNotification;
import com.tui.proof.model.rabbitMq.ClientNotification;
import com.tui.proof.model.rabbitMq.OrderNotification;
import com.tui.proof.model.rabbitMq.OrderNotificationStatus;
import java.util.UUID;

import java.time.Instant;

public class OrderMapper {

    public static Order mappingFromOrderDtoToOrder(OrderDto orderDto, Client client, Address address) {
        Order order = new Order();

        order.setClient(client);
        order.setDeliveryAddress(address);
        order.setPilotes(Integer.parseInt(orderDto.getPilotes()));
        order.setOrderTotal(orderDto.getOrderTotal());
        order.setNumber(UUID.randomUUID().toString()); // create generate OrderNumber
        order.setProcessTime(Instant.now());

        return order;
    }

    public static OrderNotification mappingFromOrderToOrderNotification(Order order, Address address, Client client, OrderNotificationStatus status) {
        OrderNotification orderNotification = new OrderNotification();

        orderNotification.setOrderTotal(order.getOrderTotal());
        orderNotification.setPilotes(order.getPilotes());
        orderNotification.setNumber(order.getNumber());
        orderNotification.setProcessTime(order.getProcessTime());
        orderNotification.setStatus(status);

        AddressNotification addressNotification = new AddressNotification();
        addressNotification.setCity(address.getCity());
        addressNotification.setCountry(address.getCountry());
        addressNotification.setPostcode(address.getPostcode());
        addressNotification.setStreet(address.getStreet());
        orderNotification.setAddressNotification(addressNotification);

        ClientNotification clientNotification = new ClientNotification();
        clientNotification.setFirstName(client.getFirstName());
        clientNotification.setLastName(client.getLastName());
        clientNotification.setTelephone(client.getTelephone());
        orderNotification.setClientNotification(clientNotification);

        return orderNotification;
    }

    public static OrderResponse mappingFromOrderToOrderResponse (Order order) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setOrderTotal(order.getOrderTotal());
        orderResponse.setNumber(order.getNumber());
        orderResponse.setPilotes(order.getPilotes());
        orderResponse.setProcessTime(order.getProcessTime());

        Address address = order.getDeliveryAddress();
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setPostcode(address.getPostcode());
        addressDto.setCountry(address.getCountry());
        addressDto.setStreet(address.getStreet());
        orderResponse.setAddress(addressDto);

        ClientDto clientDto = new ClientDto();
        Client client = order.getClient();
        clientDto.setEmail(client.getEmail());
        clientDto.setTelephone(client.getTelephone());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        orderResponse.setClient(clientDto);

        return orderResponse;
    }
}
