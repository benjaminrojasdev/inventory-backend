package com.bnrc.inventory.response;

import java.util.List;

import com.bnrc.inventory.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	
	List <Product> products;

}
