package com.productos_promociones.productos_promociones.interfaces;

import java.util.List;

import com.productos_promociones.productos_promociones.dto.ItemDto;

public interface I_ItemService {

    ItemDto createItem(ItemDto itemDto) throws Exception;
    ItemDto getItemById(Long id);
    List<ItemDto> getAllItems();
    void deleteItem(Long id);
    List<ItemDto> promotionsValidation(List<ItemDto> items);
}
