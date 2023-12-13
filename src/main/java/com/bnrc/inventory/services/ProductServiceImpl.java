package com.bnrc.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bnrc.inventory.dao.ICategoryDao;
import com.bnrc.inventory.dao.IProductDao;
import com.bnrc.inventory.model.Category;
import com.bnrc.inventory.model.Product;
import com.bnrc.inventory.response.ProductResponseRest;




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

}
