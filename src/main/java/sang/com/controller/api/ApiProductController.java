package sang.com.controller.api;

import sang.com.dto.*;
import sang.com.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ApiProductController {
    private final ProductService service;


    @GetMapping
    public List<ProductResponse> all() { return service.findAll(); }


    @GetMapping("/{id}")
    public ProductResponse one(@PathVariable Long id) { return service.findById(id); }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest req) { return service.create(req); }


    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest req) { return service.update(id, req); }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
