package sang.com.controller.api;

import sang.com.dto.*;
import sang.com.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class ApiCategoryController {
    private final CategoryService service;


    @GetMapping
    public List<CategoryResponse> all() { return service.findAll(); }


    @GetMapping("/{id}")
    public CategoryResponse one(@PathVariable Long id) { return service.findById(id); }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CategoryRequest req) { return service.create(req); }


    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @Valid @RequestBody CategoryRequest req) { return service.update(id, req); }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}