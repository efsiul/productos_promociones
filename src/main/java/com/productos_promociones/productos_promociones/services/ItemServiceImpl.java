package com.productos_promociones.productos_promociones.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productos_promociones.productos_promociones.dto.ItemDto;
import com.productos_promociones.productos_promociones.interfaces.I_ItemService;
import com.productos_promociones.productos_promociones.models.ItemModels;
import com.productos_promociones.productos_promociones.models.PromotionModels;
import com.productos_promociones.productos_promociones.repositories.ItemRepository;
import com.productos_promociones.productos_promociones.repositories.PromotionRepository;


@Service
public class ItemServiceImpl implements I_ItemService{

    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    // Mapa para almacenar las promociones encontradas
    private Map<String, PromotionModels> promotionMap = new HashMap<>();

    @Override
    public ItemDto createItem(ItemDto itemDto) throws Exception {
        // Buscar el descuento en promotions
        PromotionModels promotion = getPromotionByItemCode(itemDto.getItemCode());
        if (promotion != null) {
            Double discount = promotion.getPercentDiscount();
            itemDto.setPriceDiscount(itemDto.getPrice() * (1 - discount / 100));
        }

        ItemModels newItem = new ItemModels();
        newItem.setItemCode(itemDto.getItemCode());
        newItem.setName(itemDto.getName());
        newItem.setPrice(itemDto.getPrice());
        newItem.setPriceDiscount(itemDto.getPriceDiscount());

        ItemModels savedItem = itemRepository.save(newItem);
        return convertToDto(savedItem);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemModels> items = itemRepository.findAll();
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long id) {
        Optional<ItemModels> optionalItem = itemRepository.findById(id);
        return optionalItem.map(this::convertToDto).orElse(null);
    }

    // Método para obtener la promoción por código de ítem
    private PromotionModels getPromotionByItemCode1(String itemCode) {
        if (promotionMap.containsKey(itemCode)) {
            return promotionMap.get(itemCode);
        } else {
            PromotionModels promotion = promotionRepository.findByTriggeringItemsItemCode(itemCode);
            if (promotion != null) {
                promotionMap.put(itemCode, promotion);
            }
            return promotion;
        }
    }

    // Encontrar la promoción con el percentDiscount más alto, si hay varias promociones para el mismo ítem
    private PromotionModels getPromotionByItemCode(String itemCode) {
        List<PromotionModels> promotions = promotionRepository.findAllByTriggeringItemsItemCode(itemCode);
        
        if (!promotions.isEmpty()) {
            PromotionModels highestDiscountPromotion = promotions.stream()
                .max(Comparator.comparingDouble(PromotionModels::getPercentDiscount))
                .orElse(null);
            
            return highestDiscountPromotion;
        } else {
            return null;
        }
    }


    // Método para convertir de Entity a DTO
    private ItemDto convertToDto(ItemModels item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemCode(item.getItemCode());
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setPriceDiscount(item.getPriceDiscount());
        return itemDto;
    }

    // Método para realizar la validación de promociones
    public List<ItemDto> promotionsValidation(List<ItemDto> items) {
        for (ItemDto item : items) {
            PromotionModels promotion = getPromotionByItemCode(item.getItemCode());
            if (promotion != null) {
                Double discount = promotion.getPercentDiscount();
                item.setPriceDiscount(item.getPrice() * (1 - discount / 100));
            }
        }
        return items;
    }
}