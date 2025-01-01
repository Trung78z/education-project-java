package com.api.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.dto.product.ProductBrandDTO;
import com.api.backend.dto.product.ProductDTO;
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
                    .map(product -> {


                                ProductDTO productDTO = new ProductDTO();

                                productDTO.setId(product.getId());
                                productDTO.setName(product.getName());
                                productDTO.setPrice(product.getPrice());
                                productDTO.setQuantity(product.getQuantity());
                                productDTO.setImage(product.getImage());
                                productDTO.setDescription(product.getDescription());
                                productDTO.setDiscount(product.getDiscount());
                                productDTO.setProductBrand(product.getProductBrand() != null
                                        ? new ProductBrandDTO(product.getProductBrand().getId(), product.getProductBrand().getName())
                                        : null);
                                productDTO.setInterior(product.getInterior());
                                productDTO.setExterior(product.getExterior());
                                productDTO.setSafety(product.getSafety());
                                productDTO.setComfortConvenience(product.getComfortConvenience());
                                productDTO.setOverview(product.getOverview());
                                productDTO.setDimensionsCapacity(product.getDimensionsCapacity());
                                productDTO.setEngineAndTransmission(product.getEngineAndTransmission());

                                productDTO.setType(product.getType());
                                productDTO.setOdometer(product.getOdometer());
                                productDTO.setGearshift(product.getGearshift());

                                return productDTO;

                            }

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
                    .map(product -> {
                                ProductDTO productDTO = new ProductDTO();
                                productDTO.setId(product.getId());
                                productDTO.setName(product.getName());
                                productDTO.setPrice(product.getPrice());
                                productDTO.setQuantity(product.getQuantity());
                                productDTO.setImage(product.getImage());
                                productDTO.setDescription(product.getDescription());
                                productDTO.setDiscount(product.getDiscount());
                                productDTO.setProductBrand(product.getProductBrand() != null
                                        ? new ProductBrandDTO(product.getProductBrand().getId(), product.getProductBrand().getName())
                                        : null);
                                productDTO.setInterior(product.getInterior());
                                productDTO.setExterior(product.getExterior());
                                productDTO.setSafety(product.getSafety());
                                productDTO.setComfortConvenience(product.getComfortConvenience());
                                productDTO.setOverview(product.getOverview());
                                productDTO.setDimensionsCapacity(product.getDimensionsCapacity());
                                productDTO.setEngineAndTransmission(product.getEngineAndTransmission());

                                productDTO.setType(product.getType());
                                productDTO.setOdometer(product.getOdometer());
                                productDTO.setGearshift(product.getGearshift());

                                return productDTO;

                            }

                    )

                    .collect(Collectors.toList());

            return new ProductBrandDTO(productBrand.getId(), productBrand.getName(), productDTOs);
        } else {
            throw new RuntimeException("Product Brand not found");
        }

    }

    public ProductBrand saveProductBrand(ProductBrand productBrand) {
        ProductBrand optionalProductBrand = productBrandRepository.findByName(productBrand.getName());
        if (optionalProductBrand != null) {
            throw new RuntimeException("Product Brand already exists");
        }
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
        Optional<ProductBrand> optionalProductBrand = productBrandRepository.findById(id);
        if (!optionalProductBrand.isPresent()) {
            throw new RuntimeException("Product Brand not found");
        }

        productBrandRepository.deleteById(id);
    }
}
