package com.productos_promociones.productos_promociones.models;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.productos_promociones.productos_promociones.dto.PromotionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "promotions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionModels {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "percent_discount")
    private double percentDiscount;

    @ManyToMany
    @JoinTable(
        name = "promotion_items",
        joinColumns = @JoinColumn(name = "promotion_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemModels> triggeringItems;


}