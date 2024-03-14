package com.productos_promociones.productos_promociones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productos_promociones.productos_promociones.models.PromotionModels;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionModels, String> {
    
    @Query("SELECT p FROM PromotionModels p JOIN p.triggeringItems t WHERE t.itemCode = :itemCode")
    PromotionModels findByTriggeringItemsItemCode(@Param("itemCode") String itemCode);

    @Query("SELECT p FROM PromotionModels p JOIN p.triggeringItems t WHERE t.itemCode = :itemCode")
    List<PromotionModels> findAllByTriggeringItemsItemCode(@Param("itemCode") String itemCode);
}