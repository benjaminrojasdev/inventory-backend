package com.bnrc.inventory.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.bnrc.inventory.model.Product;
import com.bnrc.inventory.response.ProductResponseRest;
import com.bnrc.inventory.services.IProductService;
import com.bnrc.inventory.util.Util;


@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1")
@RestController
public class ProductRestController {
	
	
	private IProductService service;
	
	public ProductRestController(IProductService service) {
		super();
		this.service = service;
	}

	
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("quantity") int quantity,
		    @RequestParam("categoryId") Long categoryID) throws IOException
	{
		
		
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = service.save(product, categoryID);
		
		return response;
		
		  

	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchProductsById(@PathVariable Long id){
		  
		ResponseEntity<ProductResponseRest> response = service.searchById(id);
		return response;
	}
	
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
		  
		ResponseEntity<ProductResponseRest> response = service.searchByName(name);
		return response;
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> delete( @PathVariable Long id){
		  
		ResponseEntity<ProductResponseRest> response = service.deleteById(id);
		return response;
	}
	
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search(){
		  
		ResponseEntity<ProductResponseRest> response = service.search();
		return response;
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("quantity") int quantity,
		    @RequestParam("categoryId") Long categoryID, @PathVariable Long id) throws IOException
	{
		
		
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = service.update(product, categoryID, id);
		
		return response;
		
		  

	}
}
