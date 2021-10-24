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

    public static Order mappingFromOrderDtoToOrder(OrderRequest orderRequest, Client client, Address address) {
        Order order = new Order();

        order.setClient(client);
        order.setDeliveryAddress(address);
        order.setPilotes(Integer.parseInt(orderRequest.getPilotes()));
        order.setOrderTotal(orderRequest.getOrderTotal());
        order.setNumber(UUID.randomUUID().toString());
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
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity(address.getCity());
        addressRequest.setPostcode(address.getPostcode());
        addressRequest.setCountry(address.getCountry());
        addressRequest.setStreet(address.getStreet());
        orderResponse.setAddress(addressRequest);

        ClientRequest clientRequest = new ClientRequest();
        Client client = order.getClient();
        clientRequest.setEmail(client.getEmail());
        clientRequest.setTelephone(client.getTelephone());
        clientRequest.setFirstName(client.getFirstName());
        clientRequest.setLastName(client.getLastName());
        orderResponse.setClient(clientRequest);

        return orderResponse;
    }
    public static Order mappingFromOrderRequestToExistOrder(OrderRequest orderRequest, Order order) {
        order.setPilotes(Integer.parseInt(orderRequest.getPilotes()));
        order.setOrderTotal(orderRequest.getOrderTotal());

        return order;
    }
}
