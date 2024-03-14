package com.productos_promociones.productos_promociones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productos_promociones.productos_promociones.repositories.PromotionRepository;


import com.productos_promociones.productos_promociones.dto.PromotionDto;
import com.productos_promociones.productos_promociones.dto.PromotionDto.TriggeringItemDto;
import com.productos_promociones.productos_promociones.interfaces.I_PromotionService;
import com.productos_promociones.productos_promociones.models.PromotionModels;
import com.productos_promociones.productos_promociones.models.TriggeringItemModels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements I_PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public void loadPromotionsFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String itemsJson = new String(Files.readAllBytes(Paths.get("data/promotion.json")));
        PromotionDto[] promotionsDto = mapper.readValue(itemsJson, PromotionDto[].class);

        List<PromotionModels> promotions = mapDtosToModels(promotionsDto);
        promotionRepository.saveAll(promotions);
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        List<PromotionModels> promotions = promotionRepository.findAll();
        return mapModelsToDtos(promotions);
    }

    private List<PromotionModels> mapDtosToModels(PromotionDto[] promotionsDto) {
        return Arrays.stream(promotionsDto).map(promotionDto -> {
            PromotionModels promotion = new PromotionModels();
            promotion.setId(promotionDto.getId());
            promotion.setDescription(promotionDto.getDescription());
            promotion.setPercentDiscount(promotionDto.getPercentDiscount());
            promotion.setTriggeringItems(
                promotionDto.getTriggeringItems().stream().map(triggeringItemDto -> {
                    TriggeringItemModels triggeringItem = new TriggeringItemModels();
                    triggeringItem.setId(Long.valueOf(triggeringItemDto.getId())); 
                    triggeringItem.setPromotion(promotion);
                    return triggeringItem;
                }).collect(Collectors.toList())
            );
            return promotion;
        }).collect(Collectors.toList());
    }

private List<PromotionDto> mapModelsToDtos(List<PromotionModels> promotions) {
    return promotions.stream().map(promotion -> {
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setId(promotion.getId());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setPercentDiscount(promotion.getPercentDiscount());
        promotionDto.setTriggeringItems(
            // Indique los tipos genéricos aquí:
            promotion.getTriggeringItems().stream().<TriggeringItemDto>map(triggeringItem -> {
                TriggeringItemDto triggeringItemDto = new TriggeringItemDto();
                triggeringItemDto.setId(String.valueOf(triggeringItem.getId())); 
                triggeringItemDto.setItemCode(triggeringItem.getItemCode());
                return triggeringItemDto;
            }).collect(Collectors.toList())
        );
        return promotionDto;
    }).collect(Collectors.toList());
}
}