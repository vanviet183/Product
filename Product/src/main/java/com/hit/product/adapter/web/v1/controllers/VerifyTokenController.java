package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.applications.events.RegistrationCompleteEvent;
import com.hit.product.applications.exceptions.NotFoundException;
import com.hit.product.applications.repositories.VerificationTokenRepository;
import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domain.dtos.PasswordDto;
import com.hit.product.domain.dtos.UserDto;
import com.hit.product.domain.entities.User;
import com.hit.product.domain.entities.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
public class VerifyTokenController {

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/verifyRegistration")
    public ResponseEntity<?> verifyRegistration(@RequestParam("token") String token) {
        String request = verificationTokenService.validateVerificationToken(token);
        if(request.equalsIgnoreCase("valid")) {
            userService.save(userService.getUserByToken(token));
//            userService.saveVerificationTokenForUser(token, );
            return ResponseEntity.ok().body("User Verifies Successfully");
        }
        return ResponseEntity.ok().body("Bad User");
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<?> resendVerifyToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = verificationTokenService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return ResponseEntity.ok().body("Verification Link Sent");
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl
                + "/verifyRegistration?token="
                + verificationToken.getToken();
        emailSenderService.sendSimpleEmail(user.getEmail(), url, "Verify Register VitApp Web");
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
