package com.solacsuicini.catalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solacsuicini.catalog.entities.Category;
import com.solacsuicini.catalog.repositories.CategoryRepository;
import com.solacsuicini.catalog.services.exceptions.EntityNotFoundException;

import dto.CategoryDTO;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> list = repository.findAll();
    List<CategoryDTO> listDTO = list.stream().map(cat -> new CategoryDTO(cat)).collect(Collectors.toList());
    return listDTO;
  }

  @Transactional(readOnly=true)
  public CategoryDTO findById(Long id) {
    Optional<Category> optionalCat = repository.findById(id);
    Category entityCat = optionalCat.orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
    return new CategoryDTO(entityCat);

  }
   
}
