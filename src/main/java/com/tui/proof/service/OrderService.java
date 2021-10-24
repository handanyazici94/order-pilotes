package com.tui.proof.service;

import com.tui.proof.dto.OrderRequest;
import com.tui.proof.dto.OrderResponse;
import com.tui.proof.exception.ApiException;
import com.tui.proof.model.entity.Address;
import com.tui.proof.model.entity.Client;
import com.tui.proof.model.entity.Order;
import com.tui.proof.model.rabbitMq.OrderNotification;
import com.tui.proof.model.rabbitMq.OrderNotificationStatus;
import com.tui.proof.repository.AddressRepository;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.service.mapper.AddressMapper;
import com.tui.proof.service.mapper.OrderMapper;
import com.tui.proof.service.producer.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    public static final Integer VALIDITY_TIME = 5;

    @Autowired
    ClientService clientService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RabbitMqProducer rabbitMqProducer;


    public OrderResponse createNewOrder (OrderRequest orderRequest, Client client) {
        Address address = AddressMapper.mappingAddressRequestToAddress(orderRequest.getAddress());
        address = addressRepository.save(address);
        Order order = OrderMapper.mappingFromOrderDtoToOrder(orderRequest, client, address);
        order = orderRepository.save(order);

        OrderNotification orderNotification = OrderMapper.mappingFromOrderToOrderNotification(order, address, client, OrderNotificationStatus.PROCESS);
        rabbitMqProducer.sendOrder(orderNotification);

        return OrderMapper.mappingFromOrderToOrderResponse(order);
    }

    public OrderResponse updateOrder (Order order, Client client, OrderRequest orderRequest) throws ApiException {
        Instant limitTime = Instant.now().minus(VALIDITY_TIME, ChronoUnit.MINUTES);
        Order existingOrder = this.findOrderThatProcessTimeGreaterThanLimitTime(order.getId(), limitTime);

        Address address = AddressMapper.mappingFromAddressRequestToExistAddress(orderRequest.getAddress(), existingOrder.getDeliveryAddress());
        existingOrder = OrderMapper.mappingFromOrderRequestToExistOrder(orderRequest, order);
        existingOrder.setDeliveryAddress(address);
        existingOrder = orderRepository.save(existingOrder);

        OrderNotification orderNotification = OrderMapper.mappingFromOrderToOrderNotification(existingOrder, address, client, OrderNotificationStatus.UPDATE);
        rabbitMqProducer.sendOrder(orderNotification);

        return OrderMapper.mappingFromOrderToOrderResponse(existingOrder);
    }

    public Order findOrderThatProcessTimeGreaterThanLimitTime (Long orderId, Instant limitTime) throws ApiException {
       return orderRepository.findByIdAndProcessTimeGreaterThanEqual(orderId, limitTime)
            .orElseThrow(()-> new ApiException("The order couldn't be changed anymore"));
    }

    public Order findOrderById (Long orderId) throws ApiException {
        return orderRepository.findById(orderId).orElseThrow(()-> new ApiException("The order couldn't be found"));
    }

    public List<OrderResponse> listAllOrder () {
        return orderRepository.findAll()
            .stream()
            .map(order -> OrderMapper.mappingFromOrderToOrderResponse(order))
            .collect(Collectors.toList());
    }

    public List<OrderResponse> searchOrderAsPartial(String partialSearch) {

        partialSearch = partialSearch.replace(" ", "");
        return orderRepository.findAllByInputString(partialSearch).stream()
                .map(searchOrder -> OrderMapper.mappingFromOrderToOrderResponse(searchOrder))
                .collect(Collectors.toList());
    }

}
