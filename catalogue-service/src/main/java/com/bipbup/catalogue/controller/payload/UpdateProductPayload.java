package com.bipbup.catalogue.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull(message = "{catalogue.product.edit.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.product.edit.errors.title_is_invalid}")
        String title,
        @Size(max = 1000, message = "{catalogue.product.edit.errors.details_is_invalid}")
        String details) {
}
