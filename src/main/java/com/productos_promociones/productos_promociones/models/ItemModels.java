package com.productos_promociones.productos_promociones.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemModels {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "item_code")
    private String itemCode;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
    private double price;
}