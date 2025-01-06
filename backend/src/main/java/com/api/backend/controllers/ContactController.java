package com.api.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.backend.dto.ContactDTO;
import com.api.backend.services.EmailService;
import com.api.backend.utils.ResponseWrapper;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/api/v1/contact")
public class ContactController {
    @Autowired
    final private EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<ResponseWrapper<String>> sendContactEmail(@RequestBody ContactDTO contact) {
        try {
            emailService.sendContactEmail(contact.getEmail(), contact.getTopic(), contact.getPhone());
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, "Email sent successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, "We are unable to send email at the moment"));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

}
