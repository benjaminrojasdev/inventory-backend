package com.bnrc.inventory.services;

import org.springframework.http.ResponseEntity;

import com.bnrc.inventory.model.Product;
import com.bnrc.inventory.response.ProductResponseRest;

public interface IProductService {
	
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
	
	public ResponseEntity<ProductResponseRest> searchById(Long Id);
	
	public ResponseEntity<ProductResponseRest> searchByName(String name);
	
	public ResponseEntity<ProductResponseRest> deleteById(Long Id);
	
	public ResponseEntity<ProductResponseRest> search();
	
	public ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long Id);
	

}
