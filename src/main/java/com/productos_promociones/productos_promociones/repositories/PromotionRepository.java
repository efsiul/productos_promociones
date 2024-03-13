package com.productos_promociones.productos_promociones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos_promociones.productos_promociones.models.PromotionModels;

public interface PromotionRepository extends JpaRepository<PromotionModels, Long>{
    List<PromotionModels> findAll();
}
