package com.productos_promociones.productos_promociones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos_promociones.productos_promociones.models.ItemModels;

@Repository
public interface ItemRepository extends JpaRepository<ItemModels, Long> {
}
