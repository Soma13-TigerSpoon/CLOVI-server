package com.clovi.item.size.repository;

import com.clovi.item.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Optional<Size> findByName(String name);
}