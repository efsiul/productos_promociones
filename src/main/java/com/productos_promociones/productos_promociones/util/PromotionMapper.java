package com.productos_promociones.productos_promociones.util;


import com.productos_promociones.productos_promociones.dto.PromotionDTO;
import com.productos_promociones.productos_promociones.models.PromotionModels;

public class PromotionMapper {

    public static PromotionDTO convertToDto(PromotionModels promotion) {
        PromotionDTO dto = new PromotionDTO();
        dto.setId(promotion.getId());
        dto.setDescription(promotion.getDescription());
        dto.setPercentDiscount(promotion.getPercentDiscount());
        return dto;
    }

    public static PromotionModels convertToEntity(PromotionDTO dto) {
        PromotionModels promotion = new PromotionModels();
        promotion.setId(dto.getId());
        promotion.setDescription(dto.getDescription());
        promotion.setPercentDiscount(dto.getPercentDiscount());
        return promotion;
    }
}
