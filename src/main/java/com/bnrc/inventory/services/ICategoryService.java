package com.bnrc.inventory.services;

import org.springframework.http.ResponseEntity;

import com.bnrc.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();

}
