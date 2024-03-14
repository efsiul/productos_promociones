package com.productos_promociones.productos_promociones.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PromotionDto {
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("percentDiscount")
    private Double percentDiscount;

    @JsonProperty("triggeringItems")
    private List<TriggeringItemDto> triggeringItems;

    @Data
    public static class TriggeringItemDto {
        private String id;
        private String itemCode;

    }
}