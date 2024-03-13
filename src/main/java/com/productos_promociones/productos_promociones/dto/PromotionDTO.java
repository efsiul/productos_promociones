package com.productos_promociones.productos_promociones.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PromotionDTO {
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("percentDiscount")
    private double percentDiscount;

    @JsonProperty("triggeringItems")
    private List<ItemDTO> triggeringItems;


}