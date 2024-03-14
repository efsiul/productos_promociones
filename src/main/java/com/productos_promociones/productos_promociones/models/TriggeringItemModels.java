package com.productos_promociones.productos_promociones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "triggering_items")
public class TriggeringItemModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming numeric ID
    private Long id;

    private String itemId;

    private String itemCode;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private PromotionModels promotion;

}