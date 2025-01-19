package com.api.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.dto.transaction.TransactionDTO;
import com.api.backend.dto.transaction.TransactionRequest;
import com.api.backend.models.Transaction;
import com.api.backend.models.product.Product;
import com.api.backend.models.user.Users;
import com.api.backend.services.EmailService;
import com.api.backend.services.JWTService;
import com.api.backend.services.ProductService;
import com.api.backend.services.TransactionService;
import com.api.backend.services.UserService;
import com.api.backend.utils.ResponseWrapper;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JWTService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<TransactionRequest>> createTransaction(
            @RequestBody TransactionDTO transaction, HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization").substring(7);
            String username = tokenService.extractUserName(token);

            Users user = userService.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.UNAUTHORIZED.value(), "User not found"),
                        HttpStatus.UNAUTHORIZED);
            }

            Integer productId = transaction.getProductId();
            Product product = productService.getProductById(productId);
            if (product == null) {
                return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.NOT_FOUND.value(), "Product not found"),
                        HttpStatus.NOT_FOUND);
            }

            int requestedQuantity = transaction.getQuantity();
            if (product.getQuantity() < requestedQuantity) {
                return new ResponseEntity<>(
                        new ResponseWrapper<>(HttpStatus.BAD_REQUEST.value(), "Not enough product quantity"),
                        HttpStatus.BAD_REQUEST);
            }

            product.setQuantity(product.getQuantity() - requestedQuantity);
            productService.updateProduct(product);

            Transaction newTransaction = new Transaction();
            newTransaction.setProduct(product);
            newTransaction.setUser(user);
            newTransaction.setQuantity(requestedQuantity);
            newTransaction.setTotalPrice(transaction.getTotalPrice());
            Transaction createdTransaction = transactionService.createTransaction(newTransaction);
            emailService.sendOrderConfirmationEmail(user.getEmail(), createdTransaction.getId().toString(),
                    product.getName(),
                    requestedQuantity, transaction.getTotalPrice());
            TransactionRequest transactionRequest = convertToTransactionRequest(createdTransaction);

            return new ResponseEntity<>(new ResponseWrapper<>(true, HttpStatus.CREATED.value(), transactionRequest),
                    HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, "We are unable to send email at the moment"));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<TransactionRequest>>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            List<TransactionRequest> transactionRequests = transactions.stream()
                    .map(this::convertToTransactionRequest)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(new ResponseWrapper<>(true, HttpStatus.OK.value(), transactionRequests),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private TransactionRequest convertToTransactionRequest(Transaction transaction) {
        Users user = transaction.getUser();
        Product product = transaction.getProduct();
        return new TransactionRequest(
                transaction.getId(),
                user.getId(),
                user.getEmail(),
                product.getId(),
                product.getName(),
                transaction.getQuantity(),
                transaction.getTotalPrice(),
                transaction.getCreatedAt());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Transaction>> getTransactionById(@PathVariable Integer id) {
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            if (transaction != null) {
                return new ResponseEntity<>(new ResponseWrapper<>(true, HttpStatus.OK.value(), transaction),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ResponseWrapper<>(HttpStatus.NOT_FOUND.value(), "Transaction not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
