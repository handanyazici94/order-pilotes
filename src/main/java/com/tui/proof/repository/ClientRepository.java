package com.tui.proof.repository;


import com.tui.proof.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByTelephone(String telephone);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByIdAndOrdersId(Long id, Long orderId);

}
