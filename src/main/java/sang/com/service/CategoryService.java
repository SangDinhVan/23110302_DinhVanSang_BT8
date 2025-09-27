package sang.com.service;

import sang.com.dto.*;
import java.util.List;


public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse findById(Long id);
    CategoryResponse create(CategoryRequest req);
    CategoryResponse update(Long id, CategoryRequest req);
    void delete(Long id);
}
