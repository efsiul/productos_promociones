package com.productos_promociones.productos_promociones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemDto {

    @JsonProperty("itemCode")
    private String itemCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("priceDiscount")
    private Double priceDiscount;
}
