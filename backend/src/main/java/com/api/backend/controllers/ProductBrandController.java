package com.api.backend.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.api.backend.dto.product.ProductBrandDTO;
import com.api.backend.models.product.ProductBrand;
import com.api.backend.services.ProductBrandService;
import com.api.backend.utils.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@Controller
@WebServlet("/api/v1/brand/*")
public class ProductBrandController extends HttpServlet {

    private final ProductBrandService productBrandService;
    private final ObjectMapper objectMapper;

    public ProductBrandController(ProductBrandService productBrandService, ObjectMapper objectMapper) {
        this.productBrandService = productBrandService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<ProductBrand> products = productBrandService.getAllProductBrandsWithProducts();
            ResponseWrapper<List<ProductBrand>> responseBody = new ResponseWrapper<>(true, 200,
                    products);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        } else {
            try {
                String[] pathParts = pathInfo.split("/");
                ProductBrandDTO productBrand = productBrandService
                        .getProductBrandByIdWithProduct(pathParts[pathParts.length - 1]);
                ResponseWrapper<ProductBrandDTO> responseBody = new ResponseWrapper<>(true, 200, productBrand);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(responseBody));
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter()
                        .write(objectMapper.writeValueAsString(
                                new ResponseWrapper<>(400, e.getMessage())));
            } catch (Exception e) {
                // TODO: handle exception
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().write(
                        objectMapper.writeValueAsString(new ResponseWrapper<>(404, "Product brand not found")));
            }

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                        @Override
                        public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context)
                                throws JsonParseException {
                            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                    })
                    .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                        @Override
                        public JsonElement serialize(LocalDateTime src, Type typeOfSrc,
                                JsonSerializationContext context) {
                            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                        }
                    })
                    .create();

            ProductBrand productBrandData = gson.fromJson(reader, ProductBrand.class);
            ProductBrand productBrand = productBrandService.saveProductBrand(productBrandData);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(new ResponseWrapper<>(true, 201, productBrand)));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(new ResponseWrapper<>(400, e.getMessage())));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter()
                    .write(objectMapper.writeValueAsString(new ResponseWrapper<>(404, "Product brand not found")));
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: implement PUT method
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Product brand ID is required");
        } else {
            // TODO: PUT
            try {
                String[] pathParts = pathInfo.split("/");
                int id = Integer.parseInt(pathParts[pathParts.length - 1]);
                InputStreamReader reader = new InputStreamReader(request.getInputStream());
                Gson gson = new Gson();
                ProductBrand productBrandData = gson.fromJson(reader, ProductBrand.class);
                ProductBrand updatedProductBrand = productBrandService.updateProductBrand(id, productBrandData);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter()
                        .write(objectMapper.writeValueAsString(new ResponseWrapper<>(true, 200, updatedProductBrand)));
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter()
                        .write(objectMapper.writeValueAsString(
                                new ResponseWrapper<>(400, e.getMessage())));
            } catch (Exception e) {
                // TODO: handle exception
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().write(
                        objectMapper.writeValueAsString(new ResponseWrapper<>(404, "Product brand not found")));
            }

        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: implement DELETE method
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Product brand ID is required");
        } else {
            // TODO: DELETE
            try {
                String[] pathParts = pathInfo.split("/");
                int id = Integer.parseInt(pathParts[pathParts.length - 1]);
                productBrandService.deleteProductBrand(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter()
                        .write(objectMapper.writeValueAsString(
                                new ResponseWrapper<>(400, e.getMessage())));
            } catch (Exception e) {
                // TODO: handle exception
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().write(
                        objectMapper.writeValueAsString(new ResponseWrapper<>(404, "Product brand not found")));
            }
        }
    }
}
