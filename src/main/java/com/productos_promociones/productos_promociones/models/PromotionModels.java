package com.productos_promociones.productos_promociones.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "promotions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionModels {
    
    @Id
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "percent_discount")
    private Double percentDiscount;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL) 
    private List<TriggeringItemModels> triggeringItems = new ArrayList<>();

    public void addTriggeringItem(TriggeringItemModels item) {
        item.setPromotion(this);
        triggeringItems.add(item);
    }
}