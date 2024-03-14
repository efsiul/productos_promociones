package com.productos_promociones.productos_promociones.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.productos_promociones.productos_promociones.interfaces.I_PromotionService;
import com.productos_promociones.productos_promociones.dto.PromotionDto; // Add this import statement


@RestController
@RequestMapping("/promotions")
public class PromotionController {
    
    @Autowired
    private I_PromotionService promotionService;

    @PostMapping("/load")
    public void loadPromotionsFromJson() throws Exception {
        promotionService.loadPromotionsFromJson();
    }

    @GetMapping
    public List<PromotionDto> getAllPromotions() {
        return promotionService.getAllPromotions();
    }
}