package com.tui.proof.service;


import com.tui.proof.dto.AddressRequest;
import com.tui.proof.dto.OrderRequest;
import com.tui.proof.dto.OrderResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.model.entity.Address;
import com.tui.proof.model.entity.Client;
import com.tui.proof.model.entity.Order;
import com.tui.proof.repository.AddressRepository;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.service.producer.RabbitMqProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ClientService clientService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private RabbitMqProducer rabbitMqProducer;

    @Autowired
    private OrderService orderService;

    private Order order;
    private OrderRequest orderRequest;
    private AddressRequest addressRequest;
    private Address address;
    private Client client;

    private final Long clientId = 1L;
    private final Long orderId = 1L;
    private final Long addressId = 1L;
    private final Instant limitTime = Instant.now();


    @BeforeEach
    void createClient() {
        orderRequest = new OrderRequest();
        orderRequest.setPilotes("5");
        orderRequest.setOrderTotal(34.6);

        addressRequest = new AddressRequest();
        addressRequest.setCity("ankara");
        addressRequest.setCountry("turkey");
        addressRequest.setStreet("izmir street");
        addressRequest.setPostcode("060018");
        orderRequest.setAddress(addressRequest);

        // -----
        address = new Address();
        address.setId(addressId);
        address.setCity("ankara");
        address.setCountry("turkey");
        address.setStreet("izmir street");
        address.setPostcode("060018");

        client = new Client();
        client.setId(clientId);
        client.setFirstName("Handan");
        client.setLastName("Yayla");
        client.setTelephone("05556990924");
        client.setEmail("handanyayla@gmail.com");

        order = new Order();
        order.setId(orderId);
        order.setOrderTotal(34.6);
        order.setPilotes(5);
        order.setDeliveryAddress(address);
        order.setClient(client);
        //------
    }


    @Test
    @DisplayName("createNewOrder_validOrderRequest_returnOrderResponse")
    public void createNewOrder_validOrderRequest_returnOrderResponse() throws ApiException {
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponse actualOrderResponse = orderService.createNewOrder(orderRequest, client);

        assertEquals(order.getId(), actualOrderResponse.getId());
        assertEquals(order.getPilotes(), actualOrderResponse.getPilotes());
        assertEquals(order.getOrderTotal(), actualOrderResponse.getOrderTotal());
        assertEquals(order.getDeliveryAddress().getCity(), actualOrderResponse.getAddress().getCity());
        assertEquals(order.getDeliveryAddress().getCountry(), actualOrderResponse.getAddress().getCountry());
        assertEquals(order.getDeliveryAddress().getPostcode(), actualOrderResponse.getAddress().getPostcode());
        assertEquals(order.getDeliveryAddress().getStreet(), actualOrderResponse.getAddress().getStreet());
    }
/*
    @Test
    @DisplayName("createNewOrder_validClientIdAndOrderId_returnOrderResponse")
    public void createNewOrder_inValidClientId_returnClientIsNotFoundException() throws ApiException {
        //when(clientService.findClientById(clientId)).thenThrow(new ApiException("Client is not found"));

        ApiException actualException = assertThrows(ApiException.class, () -> {
            orderService.createNewOrder(orderRequest, client);
        });
        assertEquals("Client is not found", actualException.getMessage());

        //verify(eventPublisher, never()).fireEvent(any());
    }
*/
    @Test
    @DisplayName("listAllOrder_returnList")
    public void listAllOrder_returnList() {
        //when(orderRepository.findAll()).thenReturn(List);
    }

    @Test
    @DisplayName("searchOrderAsPartial_validPartialString_returnOrderResponse")
    public void searchOrderAsPartial_validPartialString_returnOrderResponse() {

    }

    @Test
    @DisplayName("findOrderThatProcessTimeGreaterThanLimitTime_invalidOrderId_theOrderCouldNotBeenChangedAnymoreException")
    public void findOrderThatProcessTimeGreaterThanLimitTime_invalidTime_theOrderCouldNotBeenChangedAnymoreException () {
        when(orderRepository.findByIdAndProcessTimeGreaterThanEqual(any(Long.class), any(Instant.class))).thenReturn(Optional.empty());

        ApiException actualException = assertThrows(ApiException.class, () -> {
            orderService.findOrderThatProcessTimeGreaterThanLimitTime(orderId, limitTime);
        });

        assertEquals("The order couldn't been changed anymore.", actualException.getMessage());
    }

    @Test
    @DisplayName("findOrderThatProcessTimeGreaterThanLimitTime_validTime_returnOrder")
    public void findOrderThatProcessTimeGreaterThanLimitTime_validTime_returnOrder () throws ApiException {
        when(orderRepository.findByIdAndProcessTimeGreaterThanEqual(any(Long.class), any(Instant.class))).thenReturn(Optional.of(order));

        Order actualOrder = orderService.findOrderThatProcessTimeGreaterThanLimitTime(orderId, limitTime);

        assertSame(order, actualOrder);
    }
}
