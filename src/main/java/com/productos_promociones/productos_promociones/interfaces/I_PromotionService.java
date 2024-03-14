package com.productos_promociones.productos_promociones.interfaces;

import java.util.List;

import com.productos_promociones.productos_promociones.dto.PromotionDto;

public interface I_PromotionService {

    void loadPromotionsFromJson() throws Exception;
    List<PromotionDto> getAllPromotions();
}