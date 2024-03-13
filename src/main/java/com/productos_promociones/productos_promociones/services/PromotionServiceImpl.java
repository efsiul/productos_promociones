package com.productos_promociones.productos_promociones.services;

import com.productos_promociones.productos_promociones.repositories.PromotionRepository;
import com.productos_promociones.productos_promociones.util.PromotionMapper;
import com.productos_promociones.productos_promociones.dto.PromotionDTO;
import com.productos_promociones.productos_promociones.interfaces.PromotionService;
import com.productos_promociones.productos_promociones.models.PromotionModels;

import java.util.List;

import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<PromotionDTO> getAllPromotions() {
        return promotionRepository.findAll().stream()
                .map(PromotionMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDTO getPromotionById(Long id) {
        java.util.Optional<PromotionModels> optionalPromotion = promotionRepository.findById(id);
        return optionalPromotion.map(this::convertToDTO).orElse(null);
    }

    @Override
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        PromotionModels newPromotion = convertToModel(promotionDTO);
        newPromotion = promotionRepository.save(newPromotion);
        return convertToDTO(newPromotion);
    }

    @Override
    public PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO) {
        java.util.Optional<PromotionModels> optionalPromotion = promotionRepository.findById(id);
        if (optionalPromotion.isPresent()) {
            PromotionModels existingPromotion = optionalPromotion.get();
            existingPromotion.setDescription(promotionDTO.getDescription());
            existingPromotion.setPercentDiscount(promotionDTO.getPercentDiscount());
            // Set triggeringItems accordingly if needed
            existingPromotion = promotionRepository.save(existingPromotion);
            return convertToDTO(existingPromotion);
        } else {
            return null;
        }
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    private PromotionDTO convertToDTO(PromotionModels promotion) {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setDescription(promotion.getDescription());
        promotionDTO.setPercentDiscount(promotion.getPercentDiscount());
        // Set triggeringItems accordingly if needed
        return promotionDTO;
    }

    private PromotionModels convertToModel(PromotionDTO promotionDTO) {
        PromotionModels promotion = new PromotionModels();
        promotion.setId(promotionDTO.getId());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setPercentDiscount(promotionDTO.getPercentDiscount());
        // Set triggeringItems accordingly if needed
        return promotion;
    }
    
    @Override
    public PromotionDTO findBestPromotionForItem(String itemId) {
        // Implementación para encontrar la mejor promoción para un artículo dado y convertirla a DTO
        // Por ahora, devolvemos una promoción aleatoria solo para fines de demostración
        return PromotionMapper.convertToDto(promotionRepository.findAll().get(0));
    }

 }
