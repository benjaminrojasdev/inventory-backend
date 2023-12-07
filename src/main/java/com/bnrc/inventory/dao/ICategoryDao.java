package com.bnrc.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.bnrc.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

}
