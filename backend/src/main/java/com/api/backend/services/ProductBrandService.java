package com.api.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.dto.ProductBrandDTO;
import com.api.backend.dto.ProductDTO;
import com.api.backend.models.product.ProductBrand;
import com.api.backend.repositories.ProductBrandRepository;

@Service
public class ProductBrandService {
    @Autowired
    private final ProductBrandRepository productBrandRepository;

    public ProductBrandService(ProductBrandRepository productBrandRepository) {
        this.productBrandRepository = productBrandRepository;

    }

    public List<ProductBrandDTO> getAllProductBrandsWithProducts() {
        List<ProductBrand> productBrands = productBrandRepository.findAll();

        return productBrands.stream().map(productBrand -> {
            List<ProductDTO> productDTOs = productBrand.getProducts().stream()
                    .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(),
                            product.getQuantity(),
                            product.getImage(),
                            product.getDescription(), product.getModel(),
                            product.getSize(),
                            product.getType(),
                            product.getDiscount(),
                            product.getProductBrand(),
                            product.getInterior(),
                            product.getExterior(),
                            product.getSafety(),
                            product.getComfortConvenience(),
                            product.getOverview(),
                            product.getDimensionsCapacity(),
                            product.getEngineAndTransmission())

                    )

                    .collect(Collectors.toList());
            return new ProductBrandDTO(productBrand.getId(), productBrand.getName(), productDTOs);
        }).collect(Collectors.toList());
    }

    public ProductBrandDTO getProductBrandByIdWithProduct(Integer id) {
        Optional<ProductBrand> optionalProductBrand = productBrandRepository.findById(id);
        if (optionalProductBrand.isPresent()) {
            ProductBrand productBrand = optionalProductBrand.get();

            List<ProductDTO> productDTOs = productBrand.getProducts().stream()
                    .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(),
                            product.getQuantity(),
                            product.getImage(),
                            product.getDescription(), product.getModel(),
                            product.getSize(),
                            product.getType(),
                            product.getDiscount(),
                            product.getProductBrand(),
                            product.getInterior(),
                            product.getExterior(),
                            product.getSafety(),
                            product.getComfortConvenience(),
                            product.getOverview(),
                            product.getDimensionsCapacity(),
                            product.getEngineAndTransmission())

                    )
                    .collect(Collectors.toList());

            return new ProductBrandDTO(productBrand.getId(), productBrand.getName(), productDTOs);
        } else {
            throw new RuntimeException("Product Brand not found");
        }

    }

    public ProductBrand saveProductBrand(ProductBrand productBrand) {
        return productBrandRepository.save(productBrand);
    }

    public ProductBrand updateProductBrand(Integer id, ProductBrand productBrand) {
        Optional<ProductBrand> optionalProductBrand = productBrandRepository.findById(id);
        if (optionalProductBrand.isPresent()) {
            ProductBrand existingProductBrand = optionalProductBrand.get();
            existingProductBrand.setName(productBrand.getName());
            existingProductBrand.setProducts(productBrand.getProducts());
            return productBrandRepository.save(existingProductBrand);
        } else {
            throw new RuntimeException("Product Brand not found");

        }
    }

    public void deleteProductBrand(Integer id) {
        productBrandRepository.deleteById(id);
    }
}
