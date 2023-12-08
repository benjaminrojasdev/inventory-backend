package com.bnrc.inventory.services;

import org.springframework.http.ResponseEntity;

import com.bnrc.inventory.model.Category;
import com.bnrc.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	
	public ResponseEntity<CategoryResponseRest> save(Category category);
	
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);

}
