package com.api.backend.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.backend.controllers.ProductBrandController;
import com.api.backend.controllers.ProductController;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<ProductController> productControllerServlet(ProductController productController) {
        ServletRegistrationBean<ProductController> servletBean = new ServletRegistrationBean<>(productController,
                "/api/v1/product/*");
        return servletBean;
    }

    @Bean
    public ServletRegistrationBean<ProductBrandController> productBrandControllerServlet(
            ProductBrandController productBrandController) {
        ServletRegistrationBean<ProductBrandController> servletBean = new ServletRegistrationBean<>(
                productBrandController,
                "/api/v1/brand/*");
        return servletBean;
    }
}