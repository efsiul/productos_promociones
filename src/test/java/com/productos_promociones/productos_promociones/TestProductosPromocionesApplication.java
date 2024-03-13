package com.productos_promociones.productos_promociones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProductosPromocionesApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductosPromocionesApplication::main).with(TestProductosPromocionesApplication.class).run(args);
	}

}
