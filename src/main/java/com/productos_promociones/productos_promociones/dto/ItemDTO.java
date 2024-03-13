package com.productos_promociones.productos_promociones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemDTO {
    private Long id;

    @JsonProperty("itemCode")
    private String itemCode;

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("price")
    private double price;

}