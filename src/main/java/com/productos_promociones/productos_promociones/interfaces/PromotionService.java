package com.productos_promociones.productos_promociones.interfaces;

import java.util.List;

import com.productos_promociones.productos_promociones.dto.PromotionDTO;

public interface PromotionService {
    List<PromotionDTO> getAllPromotions();
    PromotionDTO findBestPromotionForItem(String itemId);
    PromotionDTO getPromotionById(Long id);
    PromotionDTO createPromotion(PromotionDTO promotionDTO);
    PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO);
    void deletePromotion(Long id);
}
