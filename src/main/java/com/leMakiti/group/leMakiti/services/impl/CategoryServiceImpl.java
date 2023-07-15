package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.CategoryDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.repository.CategoryRepository;
import com.leMakiti.group.leMakiti.services.CategoryService;
import com.leMakiti.group.leMakiti.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()){
            log.error("Category is not valid {}", categoryDto);
            throw new InvalidEntityException("La Category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(categoryDto))
        );
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null){
            log.error("Category ID is null");
            return null;
        }

        return categoryRepository.findById(id)

        .map(CategoryDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
                "Aucune Category avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Category code is null");
            return null;
        }

        return categoryRepository.findCategoryByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Category avec le CODE = " + code + "n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Category ID is null");
            return ;
        }
        categoryRepository.deleteById(id);
    }
}
