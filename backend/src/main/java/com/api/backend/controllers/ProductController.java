package com.api.backend.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.api.backend.dto.product.ProductDTO;
import org.springframework.stereotype.Controller;

import com.api.backend.models.product.*;

import com.api.backend.services.ProductService;
import com.api.backend.utils.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@Controller
@WebServlet("/api/v1/product")
public class ProductController extends HttpServlet {
        private final ProductService productService;
        private final ObjectMapper objectMapper;

        public ProductController(ProductService productService, ObjectMapper objectMapper) {
                this.productService = productService;
                this.objectMapper = objectMapper;
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                String pathInfo = request.getPathInfo();
                try {

                        if (pathInfo == null || pathInfo.equals("/")) {
                                List<ProductDTO> products = productService.GetProduct();
                                ResponseWrapper<List<ProductDTO>> responseBody = new ResponseWrapper<>(true, 200,
                                                products);
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.setContentType("application/json");
                                response.getWriter().write(objectMapper.writeValueAsString(responseBody));
                        }

                        else {
                                String[] pathParts = pathInfo.split("/");

                                Product product = productService.GetProductById(Integer.parseInt(pathParts[1]));

                                ResponseWrapper<Product> responseBody = new ResponseWrapper<>(true, 200, product);

                                response.setStatus(HttpServletResponse.SC_OK);
                                response.setContentType("application/json");
                                response.getWriter().write(objectMapper.writeValueAsString(responseBody));
                        }
                } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write(objectMapper.writeValueAsString(
                                        new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                        "Invalid product ID")));
                } catch (RuntimeException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write(objectMapper.writeValueAsString(
                                        new ResponseWrapper<>(400, e.getMessage())));
                } catch (Exception e) {
                        // TODO: handle exception
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(objectMapper.writeValueAsString(
                                        new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                        "An error occurred while processing the request")));
                }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                try {
                        InputStreamReader reader = new InputStreamReader(request.getInputStream());
                        Gson gson = new Gson();
                        Product productData = gson.fromJson(reader, Product.class);
                        Product product = productService.PostProduct(productData);

                        response.setStatus(HttpServletResponse.SC_CREATED);
                        response.getWriter()
                                        .write(objectMapper
                                                        .writeValueAsString(new ResponseWrapper<>(true, 201, product)));

                } catch (RuntimeException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter()
                                        .write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(400, e.getMessage())));
                } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter()
                                        .write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(500, "Internal Server Error")));
                        e.printStackTrace();
                }
        }

        @Override
        protected void doPut(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                String pathInfo = request.getPathInfo();
                String[] pathParts = pathInfo.split("/");

                if (pathParts.length > 2) {
                        try {

                                Integer productId = Integer.parseInt(pathParts[1]);
                                InputStreamReader reader = new InputStreamReader(request.getInputStream());
                                Gson gson = new Gson();
                                if (pathParts[2].equals("overview")) {
                                        ProductOverview productOverviewData = gson.fromJson(reader,
                                                        ProductOverview.class);
                                        Product updatedProduct = productService.updateProductOverview(productId,
                                                        productOverviewData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                } else if (pathParts[2].equals("comfortConvenience")) {
                                        Type listType = new TypeToken<List<ProductComfortConvenience>>() {
                                        }.getType();
                                        List<ProductComfortConvenience> productComfortConvenienceData = gson.fromJson(
                                                        reader,
                                                        listType);
                                        Product updatedProduct = productService.updateProductComfortConvenience(
                                                        productId,
                                                        productComfortConvenienceData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                } else if (pathParts[2].equals("safety")) {

                                        Type listType = new TypeToken<List<ProductSafety>>() {
                                        }.getType();
                                        List<ProductSafety> productSafetyData = gson.fromJson(reader, listType);
                                        Product updatedProduct = productService.updateProductSafety(productId,
                                                        productSafetyData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                } else if (pathParts[2].equals("exterior")) {
                                        Type listType = new TypeToken<List<ProductExterior>>() {
                                        }.getType();
                                        List<ProductExterior> productExteriorData = gson.fromJson(reader, listType);
                                        Product updatedProduct = productService.updateProductExterior(productId,
                                                        productExteriorData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                } else if (pathParts[2].equals("interior")) {
                                        Type listType = new TypeToken<List<ProductInterior>>() {
                                        }.getType();
                                        List<ProductInterior> productInteriorData = gson.fromJson(reader, listType);
                                        Product updatedProduct = productService.updateProductInterior(productId,
                                                        productInteriorData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                } else if (pathParts[2].equals("dimensionsCapacity")) {
                                        ProductDimensionsCapacity productDimensionsCapacityData = gson.fromJson(reader,
                                                        ProductDimensionsCapacity.class);
                                        Product updatedProduct = productService.updateProductDimensionsCapacity(
                                                        productId,
                                                        productDimensionsCapacityData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                } else if (pathParts[2].equals("engineAndTransmission")) {
                                        ProductEngineAndTransmission productEngineAndTransmissionData = gson.fromJson(
                                                        reader,
                                                        ProductEngineAndTransmission.class);

                                        Product updatedProduct = productService.updateProductEngineAndTransmission(
                                                        productId,
                                                        productEngineAndTransmissionData);
                                        response.setStatus(HttpServletResponse.SC_OK);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(true, 200, updatedProduct)));
                                }

                                else

                                {

                                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                        response.getWriter().write(objectMapper.writeValueAsString(
                                                        new ResponseWrapper<>(
                                                                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                                        "An error occurred while processing the request")));
                                }
                        } catch (NumberFormatException e) {

                                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                response.getWriter().write(objectMapper.writeValueAsString(
                                                new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                                "Invalid product ID")));
                        } catch (RuntimeException e) {
                                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                response.getWriter()
                                                .write(objectMapper.writeValueAsString(
                                                                new ResponseWrapper<>(400, e.getMessage())));
                        } catch (Exception e) {

                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                response.getWriter().write(objectMapper.writeValueAsString(
                                                new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                                "An error occurred while processing the request")));
                        }
                } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write(objectMapper.writeValueAsString(
                                        new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                        "Product ID is required")));
                }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                String pathInfo = request.getPathInfo();
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 1) {
                        try {
                                Integer productId = Integer.parseInt(pathParts[1]);
                                productService.deleteProduct(productId);
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.getWriter().write(objectMapper.writeValueAsString(
                                                new ResponseWrapper<>(true, 200, "Product deleted successfully")));
                        } catch (NumberFormatException e) {

                                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                response.getWriter().write(objectMapper.writeValueAsString(
                                                new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                                "Invalid product ID")));
                        } catch (RuntimeException e) {
                                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                response.getWriter()
                                                .write(objectMapper.writeValueAsString(
                                                                new ResponseWrapper<>(400, e.getMessage())));
                        } catch (Exception e) {

                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                response.getWriter().write(objectMapper.writeValueAsString(
                                                new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                                "An error occurred while processing the request")));
                        }
                } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write(objectMapper.writeValueAsString(
                                        new ResponseWrapper<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                                        "Product ID is required")));

                }
        }
}
