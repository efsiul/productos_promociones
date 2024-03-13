package com.productos_promociones.productos_promociones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos_promociones.productos_promociones.models.ItemModels;


public interface ItemRepository extends JpaRepository<ItemModels, Long> {
    List<ItemModels> findAll();
}
