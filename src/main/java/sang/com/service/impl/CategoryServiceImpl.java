package sang.com.service.impl;

import sang.com.domain.Category;
import sang.com.dto.*;
import sang.com.repository.CategoryRepository;
import sang.com.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;


    @Override
    public List<CategoryResponse> findAll() {
        return repo.findAll().stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName(), c.getDescription()))
                .toList();
    }


    @Override
    public CategoryResponse findById(Long id) {
        var c = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryResponse(c.getId(), c.getName(), c.getDescription());
    }


    @Override
    public CategoryResponse create(CategoryRequest req) {
        var c = Category.builder().name(req.name()).description(req.description()).build();
        c = repo.save(c);
        return new CategoryResponse(c.getId(), c.getName(), c.getDescription());
    }


    @Override
    public CategoryResponse update(Long id, CategoryRequest req) {
        var c = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        c.setName(req.name());
        c.setDescription(req.description());
        return new CategoryResponse(c.getId(), c.getName(), c.getDescription());
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}