package com.bnrc.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.bnrc.inventory.model.Product;

public interface IProductDao extends CrudRepository <Product, Long> {

}
