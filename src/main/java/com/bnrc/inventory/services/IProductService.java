package com.bnrc.inventory.services;

import org.springframework.http.ResponseEntity;

import com.bnrc.inventory.model.Product;
import com.bnrc.inventory.response.ProductResponseRest;

public interface IProductService {
	
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

}
