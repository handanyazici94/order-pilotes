package com.tui.proof.repository;

import com.tui.proof.model.entity.Client;
import com.tui.proof.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
    List<Order> findByClient(Client client);
    Optional<Order> findByIdAndProcessTimeGreaterThanEqual(Long orderId, Instant limitTime);

    @Query(value = "SELECT o FROM Order as o WHERE " +
            "(:inputString is null or lower(concat(o.client.firstName,o.client.lastName )) like lower(concat('%', :inputString,'%'))) or " +
            "(:inputString is null or o.client.telephone like concat('%', :inputString,'%')) or " +
            "(:inputString is null or lower(o.client.email) like lower(concat('%', :inputString,'%'))) or " +
            "(:inputString is null or lower(o.number) like lower(concat('%', :inputString,'%'))) or " +
            "(:inputString is null or lower(o.deliveryAddress.city) like lower(concat('%', :inputString,'%'))) or " +
            "(:inputString is null or lower(o.deliveryAddress.country) like lower(concat('%', :inputString,'%'))) or " +
            "(:inputString is null or lower(o.deliveryAddress.street) like lower(concat('%', :inputString,'%')))"
    )
    List<Order> findAllByInputString(String inputString);

}
