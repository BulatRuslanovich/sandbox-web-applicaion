package com.bipbup.manager.controller;

import com.bipbup.manager.client.ProductsRestClient;
import com.bipbup.manager.controller.payload.NewProductPayload;
import com.bipbup.manager.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductList(Model model) {
        model.addAttribute("products", this.productsRestClient.findAllProduct());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload, Model model) {
        try {
            var product = this.productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException ex) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", ex.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
