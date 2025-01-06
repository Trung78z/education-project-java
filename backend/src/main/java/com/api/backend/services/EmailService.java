package com.api.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    public void sendOrderConfirmationEmail(String to, String orderId, String productName, int quantity,
            double totalPrice) throws MessagingException {

        String htmlContent = "<html><body>"
                + "<h1>Congratulations on Your Successful Purchase!</h1>"
                + "<p>Thank you for purchasing the product <strong>" + productName + "</strong>.</p>"
                + "<p>Order Details:</p>"
                + "<ul>"
                + "<li>Order ID: " + orderId + "</li>"
                + "<li>Quantity: " + quantity + "</li>"
                + "<li>Total Price: " + totalPrice + " VND</li>"
                + "</ul>"
                + "<p>We will deliver your product to you as soon as possible.</p>"
                + "<p>Best regards,</p>"
                + "<p>Customer Support Team</p>"
                + "</body></html>";

        String storeOwnerEmailContent = "<html><body>"
                + "<h1>New Order from a Customer</h1>"
                + "<p>A new order has been successfully placed by a customer with the following details:</p>"
                + "<ul>"
                + "<li>Order ID: " + orderId + "</li>"
                + "<li>Product: " + productName + "</li>"
                + "<li>Quantity: " + quantity + "</li>"
                + "<li>Total Price: " + totalPrice + " VND</li>"
                + "<li>Customer Email: " + to + "</li>"
                + "</ul>"
                + "<p>Please process this order promptly.</p>"
                + "<p>Best regards,</p>"
                + "<p>Store Management Team</p>"
                + "</body></html>";

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("trungpyy@gmail.com");
        helper.setTo(to);
        helper.setSubject("Order Purchase Confirmation");
        helper.setText(htmlContent, true);

        emailSender.send(mimeMessage);

        MimeMessage storeOwnerMessage = emailSender.createMimeMessage();
        MimeMessageHelper storeOwnerHelper = new MimeMessageHelper(storeOwnerMessage, true);
        storeOwnerHelper.setFrom("trungpyy@gmail.com");
        storeOwnerHelper.setTo("support@hcmuss.site");
        storeOwnerHelper.setSubject("Notification of a New Order");
        storeOwnerHelper.setText(storeOwnerEmailContent, true);

        emailSender.send(storeOwnerMessage);

    }

    public void sendContactEmail(String contactEmail, String topic, String phone) throws MessagingException {

        String contactEmailContent = "<html><body>"
                + "<h1>You Have a New Contact Request</h1>"
                + "<p>We have received a contact request with the following details:</p>"
                + "<ul>"
                + "<li>Email: " + contactEmail + "</li>"
                + "<li>Topic: " + topic + "</li>"
                + "<li>Phone: " + phone + "</li>"
                + "</ul>"
                + "<p>Our team will get back to you shortly.</p>"
                + "<p>Best regards,</p>"
                + "<p>Customer Support Team</p>"
                + "</body></html>";
        MimeMessage contactMessage = emailSender.createMimeMessage();
        MimeMessageHelper contactHelper = new MimeMessageHelper(contactMessage, true);
        contactHelper.setFrom("trungpyy@gmail.com");
        contactHelper.setTo("support@hcmuss.site");
        contactHelper.setSubject("New Contact Request: " + topic);
        contactHelper.setText(contactEmailContent, true);

        // Send the email
        emailSender.send(contactMessage);
    }

}
