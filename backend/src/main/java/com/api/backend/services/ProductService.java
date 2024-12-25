package com.api.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.api.backend.models.product.Product;
import com.api.backend.models.product.ProductBrand;
import com.api.backend.models.product.ProductComfortConvenience;
import com.api.backend.models.product.ProductExterior;
import com.api.backend.models.product.ProductInterior;
import com.api.backend.models.product.ProductOverview;
import com.api.backend.models.product.ProductSafety;
import com.api.backend.models.product.ProductDimensionsCapacity;
import com.api.backend.models.product.ProductEngineAndTransmission;
import com.api.backend.repositories.ProductBrandRepository;
import com.api.backend.repositories.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public final ProductRepository productRepository;
    public final ProductBrandRepository productBrandRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(ProductRepository productRepository, ProductBrandRepository productBrandRepository) {
        this.productRepository = productRepository;
        this.productBrandRepository = productBrandRepository;

    }

    public List<Product> GetProduct() {
        return productRepository.findAll();
    }

    // Get product by id
    public Product GetProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product PostProduct(Product product) {
        try {
            Optional<ProductBrand> esxOptional = productBrandRepository.findById(product.getProductBrand().getId());
            if (esxOptional.isEmpty()) {
                throw new RuntimeException("Product Brand not found");
            }

            Product existingProduct = productRepository.findByName(product.getName());
            if (existingProduct != null
                    && existingProduct.getProductBrand().getId() == product.getProductBrand().getId()) {
                throw new RuntimeException("Product already exists");
            }

            if (product.getOverview() != null) {
                product.getOverview().setProduct(product);
            }
            if (product.getExterior() != null) {
                product.getExterior().forEach(exterior -> exterior.setProduct(product));
            }
            if (product.getInterior() != null) {
                product.getInterior().forEach(interior -> interior.setProduct(product));
            }

            if (product.getSafety() != null) {
                product.getSafety().forEach(safe -> safe.setProduct(product));
            }
            if (product.getComfortConvenience() != null) {
                product.getComfortConvenience().forEach(comfort -> comfort.setProduct(product));
            }
            if (product.getDimensionsCapacity() != null) {
                product.getDimensionsCapacity().setProduct(product);
            }
            if (product.getEngineAndTransmission() != null) {
                product.getEngineAndTransmission().setProduct(product);
            }
            product.setProductBrand(esxOptional.get());
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Foreign key constraint violated: " + e.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }

    }

    public Product updateProductOverview(Integer productId, ProductOverview productOverview) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getOverview() != null) {
            ProductOverview existingOverview = product.getOverview();
            existingOverview.setBody(productOverview.getBody());
            existingOverview.setProductCondition(productOverview.getProductCondition());
            existingOverview.setMileage(productOverview.getMileage());
            existingOverview.setEngineSize(productOverview.getEngineSize());
            existingOverview.setFuelType(productOverview.getFuelType());
            existingOverview.setDoors(productOverview.getDoors());
            existingOverview.setYear(productOverview.getYear());
            existingOverview.setCylinders(productOverview.getCylinders());
            existingOverview.setTransmission(productOverview.getTransmission());
            existingOverview.setColor(productOverview.getColor());
            existingOverview.setDriveType(productOverview.getDriveType());
            existingOverview.setVin(productOverview.getVin());
            product.setOverview(existingOverview);
        } else {
            productOverview.setProduct(product);
            product.setOverview(productOverview);
        }
        return productRepository.save(product);
    }

    public Product updateProductDimensionsCapacity(Integer productId,
            ProductDimensionsCapacity productDimensionsCapacity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getDimensionsCapacity() != null) {
            ProductDimensionsCapacity existDimensionsCapacity = product.getDimensionsCapacity();
            existDimensionsCapacity.setLength(productDimensionsCapacity.getLength());
            existDimensionsCapacity.setHeight(productDimensionsCapacity.getHeight());
            existDimensionsCapacity.setWheelbase(productDimensionsCapacity.getWheelbase());
            existDimensionsCapacity.setHeightWithRoofRails(productDimensionsCapacity.getHeightWithRoofRails());
            existDimensionsCapacity.setLuggageCapacitySeatsUp(productDimensionsCapacity.getLuggageCapacitySeatsUp());
            existDimensionsCapacity
                    .setLuggageCapacitySeatsDown(productDimensionsCapacity.getLuggageCapacitySeatsDown());
            existDimensionsCapacity.setWidth(productDimensionsCapacity.getWidth());
            existDimensionsCapacity.setWidthWithMirrors(productDimensionsCapacity.getWidthWithMirrors());
            existDimensionsCapacity.setGrossVehicleWeight(productDimensionsCapacity.getGrossVehicleWeight());
            existDimensionsCapacity.setMaxLoadingWeight(productDimensionsCapacity.getMaxLoadingWeight());
            existDimensionsCapacity.setMaxRoofLoad(productDimensionsCapacity.getMaxRoofLoad());
            existDimensionsCapacity.setNumberOfSeats(productDimensionsCapacity.getNumberOfSeats());
            product.setDimensionsCapacity(existDimensionsCapacity);

        } else {
            productDimensionsCapacity.setProduct(product);
            product.setDimensionsCapacity(productDimensionsCapacity);
        }
        return productRepository.save(product);
    }

    public Product updateProductEngineAndTransmission(Integer productId,
            ProductEngineAndTransmission productEngineAndTransmission) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getEngineAndTransmission() != null) {
            ProductEngineAndTransmission existEngineAndTransmission = product.getEngineAndTransmission();
            existEngineAndTransmission.setFuelTankCapacity(productEngineAndTransmission.getFuelTankCapacity());
            existEngineAndTransmission
                    .setMaxTowingWeightBraked(productEngineAndTransmission.getMaxTowingWeightBraked());
            existEngineAndTransmission
                    .setMaxTowingWeightUnbraked(productEngineAndTransmission.getMaxTowingWeightUnbraked());
            existEngineAndTransmission.setMinimumKerbweight(productEngineAndTransmission.getMinimumKerbweight());
            existEngineAndTransmission
                    .setTurningCircleKerbToKerb(productEngineAndTransmission.getTurningCircleKerbToKerb());
            product.setEngineAndTransmission(existEngineAndTransmission);
        } else {
            productEngineAndTransmission.setProduct(product);
            product.setEngineAndTransmission(productEngineAndTransmission);
        }
        try {
            return productRepository.save(product);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Conflict: The product was updated or deleted by another transaction.", e);
        }
    }

    public Product updateProductInterior(Integer productId, List<ProductInterior> productInteriorList) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductInterior> currentInteriors = product.getInterior();
        if (currentInteriors == null) {
            currentInteriors = new ArrayList<>();
        }

        for (ProductInterior newInterior : productInteriorList) {
            if (newInterior.getId() != null) {
                boolean updated = false;
                for (ProductInterior existingInterior : currentInteriors) {
                    if (existingInterior.getId().equals(newInterior.getId())) {

                        existingInterior.setName(newInterior.getName());
                        existingInterior.setProduct(product);
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    newInterior.setProduct(product);
                    currentInteriors.add(newInterior);
                }
            } else {
                newInterior.setProduct(product);
                currentInteriors.add(newInterior);
            }
        }

        product.setInterior(currentInteriors);
        return productRepository.save(product);
    }

    public Product updateProductSafety(Integer productId, List<ProductSafety> productSafetiesList) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductSafety> currentSafeties = product.getSafety();
        if (currentSafeties == null) {
            currentSafeties = new ArrayList<>();
        }

        for (ProductSafety newSafety : productSafetiesList) {
            if (newSafety.getId() != null) {
                boolean updated = false;
                for (ProductSafety existingSafety : currentSafeties) {
                    if (existingSafety.getId().equals(newSafety.getId())) {
                        existingSafety.setName(newSafety.getName());
                        existingSafety.setProduct(product);
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    newSafety.setProduct(product);
                    currentSafeties.add(newSafety);
                }
            } else {
                newSafety.setProduct(product);
                currentSafeties.add(newSafety);
            }
        }

        product.setSafety(currentSafeties);
        return productRepository.save(product);
    }

    public Product updateProductExterior(Integer productId, List<ProductExterior> productExteriorList) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductExterior> currentExteriors = product.getExterior();
        if (currentExteriors == null) {
            currentExteriors = new ArrayList<>();
        }
        for (ProductExterior newExterior : productExteriorList) {
            if (newExterior.getId() != null) {
                boolean updated = false;
                for (ProductExterior existingExterior : currentExteriors) {
                    if (existingExterior.getId() == (newExterior.getId())) {
                        existingExterior.setName(newExterior.getName());
                        existingExterior.setProduct(product);
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    newExterior.setProduct(product);
                    currentExteriors.add(newExterior);
                }
            } else {

                newExterior.setProduct(product);
                currentExteriors.add(newExterior);
            }
        }

        product.setExterior(currentExteriors);
        return productRepository.save(product);
    }

    public Product updateProductComfortConvenience(Integer productId,
            List<ProductComfortConvenience> productComfortConvenienceList) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductComfortConvenience> currentComfortConvenience = product.getComfortConvenience();
        if (currentComfortConvenience == null) {
            currentComfortConvenience = new ArrayList<>();
        }

        for (ProductComfortConvenience newComfortConvenience : productComfortConvenienceList) {
            if (newComfortConvenience.getId() != null) {
                boolean updated = false;
                for (ProductComfortConvenience existingComfortConvenience : currentComfortConvenience) {
                    if (existingComfortConvenience.getId() == newComfortConvenience.getId()) {
                        existingComfortConvenience.setName(newComfortConvenience.getName());
                        existingComfortConvenience.setProduct(product);
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    newComfortConvenience.setProduct(product);
                    currentComfortConvenience.add(newComfortConvenience);
                }
            } else {
                newComfortConvenience.setProduct(product);
                currentComfortConvenience.add(newComfortConvenience);
            }
        }

        product.setComfortConvenience(currentComfortConvenience);
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        try {
            jdbcTemplate.update("DELETE FROM product_comfort_convenience WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM product_interior WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM product_exterior WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM product_safety WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM product_overview WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM product_dimensions_capacity WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM product_engine_and_transmission WHERE product_id = ?", id);
            jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
            productRepository.deleteById(id);

        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            throw new RuntimeException("Error deleting product", e);
        }
    }

}
