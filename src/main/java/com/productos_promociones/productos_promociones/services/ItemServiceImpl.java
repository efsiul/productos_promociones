package com.productos_promociones.productos_promociones.services;

import org.springframework.stereotype.Service;

import com.productos_promociones.productos_promociones.dto.ItemDTO;
import com.productos_promociones.productos_promociones.interfaces.ItemService;
import com.productos_promociones.productos_promociones.models.ItemModels;
import com.productos_promociones.productos_promociones.repositories.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id) {
        java.util.Optional<ItemModels> optionalItem = itemRepository.findById(id);
        return optionalItem.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        ItemModels newItem = convertToModel(itemDTO);
        newItem = itemRepository.save(newItem);
        return convertToDTO(newItem);
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        java.util.Optional<ItemModels> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemModels existingItem = optionalItem.get();
            existingItem.setItemCode(itemDTO.getItemCode());
            existingItem.setName(itemDTO.getName());
            existingItem.setPrice(itemDTO.getPrice());
            existingItem = itemRepository.save(existingItem);
            return convertToDTO(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO convertToDTO(ItemModels item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setItemCode(item.getItemCode());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    private ItemModels convertToModel(ItemDTO itemDTO) {
        ItemModels item = new ItemModels();
        item.setId(itemDTO.getId());
        item.setItemCode(itemDTO.getItemCode());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}
