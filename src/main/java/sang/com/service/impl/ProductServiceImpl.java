package sang.com.service.impl;

import sang.com.domain.Category;
import sang.com.domain.Product;
import sang.com.dto.*;
import sang.com.repository.CategoryRepository;
import sang.com.repository.ProductRepository;
import sang.com.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;


    @Override
    public List<ProductResponse> findAll() {
        return productRepo.findAll().stream().map(p -> new ProductResponse(
                p.getId(), p.getName(), p.getPrice(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getCategory() != null ? p.getCategory().getName() : null
        )).toList();
    }


    @Override
    public ProductResponse findById(Long id) {
        var p = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductResponse(
                p.getId(), p.getName(), p.getPrice(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getCategory() != null ? p.getCategory().getName() : null
        );
    }


    @Override
    public ProductResponse create(ProductRequest req) {
        Category cat = null;
        if (req.categoryId() != null) {
            cat = categoryRepo.findById(req.categoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        }
        var p = Product.builder().name(req.name()).price(req.price()).category(cat).build();
        p = productRepo.save(p);
        return new ProductResponse(p.getId(), p.getName(), p.getPrice(),
                cat != null ? cat.getId() : null, cat != null ? cat.getName() : null);
    }


    @Override
    public ProductResponse update(Long id, ProductRequest req) {
        var p = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        p.setName(req.name());
        p.setPrice(req.price());
        if (req.categoryId() != null) {
            var cat = categoryRepo.findById(req.categoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
            p.setCategory(cat);
        } else {
            p.setCategory(null);
        }
        return new ProductResponse(
                p.getId(), p.getName(), p.getPrice(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getCategory() != null ? p.getCategory().getName() : null
        );
    }


    @Override
    public void delete(Long id) { productRepo.deleteById(id); }
}