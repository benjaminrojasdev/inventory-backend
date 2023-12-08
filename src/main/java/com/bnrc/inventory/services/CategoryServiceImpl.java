package com.bnrc.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bnrc.inventory.dao.ICategoryDao;
import com.bnrc.inventory.model.Category;
import com.bnrc.inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {

		CategoryResponseRest response = new CategoryResponseRest();
		 try {
			 
			 List<Category> category = (List<Category>) categoryDao.findAll();
			 
			 response.getCategoryResponse().setCategory(category);
			 response.setMetadata("Respuesta ok","00","Respuesta exitosa");
			 
		 } catch(Exception e) {
			 
			 response.setMetadata("No hubo respuesta", "-1", "Error al consultar");
			 e.getStackTrace();
			 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		 try {
			 
			 Optional<Category> category = categoryDao.findById(id);
			 
			 if (category.isPresent()) {
				 list.add(category.get());
				 response.getCategoryResponse().setCategory(list);
				 response.setMetadata("Respuesta Ok", "00", "Categoria encontrada");
			 } else {
				 response.setMetadata("No hubo respuesta", "-1", "Categoria no encontrada");
				 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			 }
			 
		 } catch(Exception e) {
			 
			 response.setMetadata("No hubo respuesta", "-1", "Error al consultar por Id");
			 e.getStackTrace();
			 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		 try {

			 Category categorySaved = categoryDao.save(category);
			 if (categorySaved != null) {
				 list.add(categorySaved);
				 response.getCategoryResponse().setCategory(list);
				 response.setMetadata("Exitoso", "200", "Categoria guardada");
			}else {
				response.setMetadata("No hubo respuesta", "-1", "Categoria no guardada");
				 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
	 		 
		 } catch(Exception e) {
			 
			 response.setMetadata("No hubo respuesta", "-1", "Error al grabar categoria");
			 e.getStackTrace();
			 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		 try {

			 Optional<Category> categorySearch = categoryDao.findById(id);
			 
			 if (categorySearch.isPresent()) {
				 // se procedera a actualizar el registro
				 categorySearch.get().setName(category.getName());
				 categorySearch.get().setDescription(category.getDescription());
				 
				 Category categoryUpdate = categoryDao.save(categorySearch.get());
				 
				 if (categoryUpdate != null) {
					 list.add(categoryUpdate);
					 response.getCategoryResponse().setCategory(list);
					 response.getCategoryResponse().setCategory(list);
					 response.setMetadata("Exitoso", "200", "Categoria actualizada");
				 }
			 } else {
				 response.setMetadata("No hubo respuesta", "-1", "Categoria no encontrada");
				 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			 }
	 		 
		 } catch(Exception e) {
			 
			 response.setMetadata("No hubo respuesta", "-1", "Error al actualizar la categoria");
			 e.getStackTrace();
			 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		 try {
			 
			 categoryDao.deleteById(id);
			 response.setMetadata("Exitoso", "00", "Categoria eliminada exitosamente");
			 
		 } catch(Exception e) {
			 
			 response.setMetadata("No hubo respuesta", "-1", "Error al eliminarr");
			 e.getStackTrace();
			 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
