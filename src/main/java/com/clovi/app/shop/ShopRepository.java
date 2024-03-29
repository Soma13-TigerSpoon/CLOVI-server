package com.clovi.app.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByName(String shopName);
    List<Shop> findAll();

    Optional<Shop> findByHostname(String hostname);


    Optional<Shop> findByHostnameAndDeletedIsFalse(String hostname);
}
