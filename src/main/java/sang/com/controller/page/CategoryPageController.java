package sang.com.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CategoryPageController {
    @GetMapping("/admin/categories")
    public String index() { return "category/index"; }
}