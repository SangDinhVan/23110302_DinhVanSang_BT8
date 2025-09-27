package sang.com.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public record ProductRequest(
        @NotBlank String name,
        @DecimalMin("0.0") BigDecimal price,
        Long categoryId
) {}
