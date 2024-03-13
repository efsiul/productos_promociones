package com.productos_promociones.productos_promociones.interfaces;

import java.util.List;

import com.productos_promociones.productos_promociones.dto.ItemDTO;

public interface ItemService {
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(Long id);
    ItemDTO createItem(ItemDTO itemDTO);
    ItemDTO updateItem(Long id, ItemDTO itemDTO);
    void deleteItem(Long id);
}
