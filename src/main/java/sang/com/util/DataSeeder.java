package sang.com.util;

import sang.com.domain.Category;
import sang.com.domain.Product;
import sang.com.repository.CategoryRepository;
import sang.com.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final CategoryRepository catRepo;
    private final ProductRepository prodRepo;

    @Override
    public void run(String... args) {
        if (catRepo.count() == 0) {
            var c1 = catRepo.save(Category.builder().name("T-Shirt").description("Áo thun").build());
            var c2 = catRepo.save(Category.builder().name("Polo").description("Áo cổ bẻ").build());

            prodRepo.save(Product.builder().name("Basic Tee")
                    .price(new BigDecimal("149000")).category(c1).build());
            prodRepo.save(Product.builder().name("Polo Minimal")
                    .price(new BigDecimal("199000")).category(c2).build());
        }
    }
}