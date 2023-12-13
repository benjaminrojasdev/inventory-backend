package com.bnrc.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bnrc.inventory.dao.ICategoryDao;
import com.bnrc.inventory.dao.IProductDao;
import com.bnrc.inventory.model.Category;
import com.bnrc.inventory.model.Product;
import com.bnrc.inventory.response.ProductResponseRest;
import com.bnrc.inventory.util.Util;






@Service
public class ProductServiceImpl implements IProductService {
	
	
	private ICategoryDao categoryDao;
	private IProductDao productDao;
	
	public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList <>();
		
		
		try {
			
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if(category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("Fallido", "303", "Categoria no encontrada asociada al producto");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
			Product productSaved = productDao.save(product);
			
			
			if (productSaved != null) {
				list.add(productSaved);
				response.getProductResponse().setProducts(list);
				response.setMetadata("Exitoso", "00", "Producto guardado correctamente");
			} else {
				response.setMetadata("Fallido", "303", "No se pudo guardar el producto");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch(Exception e) {
			
			e.getStackTrace();
			response.setMetadata("Fallido", "303", "Error al guardar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
		
		
	}



	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductResponseRest> serachById(Long Id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList <>();
		
		
		try {
			
			Optional<Product> product = productDao.findById(Id);
			
			if(product.isPresent()) {
				
				byte [] pictureDescompressed = Util.decompressZLib(product.get().getPicture());
				product.get().setPicture(pictureDescompressed);
				list.add(product.get());
				response.getProductResponse().setProducts(list);
				response.setMetadata("Exitoso", "001", "Producto encontrado");
			} else {
				response.setMetadata("Fallido", "303", "Producto no encontrado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
			
		} catch(Exception e) {
			
			e.getStackTrace();
			response.setMetadata("Fallido", "303", "Error buscar el producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
		
	}

}
