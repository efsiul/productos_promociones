package com.productos_promociones.productos_promociones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productos_promociones.productos_promociones.interfaces.DataLoaderService;

@RestController
@RequestMapping("/data")
public class DataLoaderController {

    private final DataLoaderService dataLoaderService;

    public DataLoaderController(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @PostMapping("/load")
    public ResponseEntity<String> loadData() {
        dataLoaderService.loadData();
        return new ResponseEntity<>("Data loaded successfully", HttpStatus.OK);
    }
}