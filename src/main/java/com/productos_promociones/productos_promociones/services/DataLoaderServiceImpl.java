package com.productos_promociones.productos_promociones.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productos_promociones.productos_promociones.dto.ItemDTO;
import com.productos_promociones.productos_promociones.dto.PromotionDTO;
import com.productos_promociones.productos_promociones.interfaces.DataLoaderService;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

    private final ItemServiceImpl itemService;
    private final PromotionServiceImpl promotionService;
    private final ObjectMapper objectMapper;

    public DataLoaderServiceImpl(ItemServiceImpl itemService, PromotionServiceImpl promotionService, ObjectMapper objectMapper) {
        this.itemService = itemService;
        this.promotionService = promotionService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void loadData() {
        try {
            String itemsJson = new String(Files.readAllBytes(Paths.get("data/item.JSON")));
            List<ItemDTO> items = objectMapper.readValue(itemsJson, new TypeReference<List<ItemDTO>>() {});

            for (ItemDTO item : items) {
                itemService.createItem(item);
            }

            String promotionsJson = new String(Files.readAllBytes(Paths.get("data/promotion.JSON")));
            List<PromotionDTO> promotions = objectMapper.readValue(promotionsJson, new TypeReference<List<PromotionDTO>>() {});

            for (PromotionDTO promotion : promotions) {
                promotionService.createPromotion(promotion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}