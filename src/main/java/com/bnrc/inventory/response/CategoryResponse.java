package com.bnrc.inventory.response;

import java.util.List;

import com.bnrc.inventory.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;

}
