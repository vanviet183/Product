package com.hit.product.applications.services.impl;

import com.hit.product.applications.repositories.PasswordResetTokenRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.repositories.VerificationTokenRepository;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domain.entities.PasswordResetToken;
import com.hit.product.domain.entities.User;
import com.hit.product.domain.entities.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;


    @Override
    public void createVerificationToken(String token) {
        verificationTokenRepository.save(new VerificationToken(token));
    }

    @Override
    public String validateVerificationToken(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken.isEmpty()) {
            return "invalid";
        }

//        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.get().getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken.get());
            return "expired";
        }

//        userRepository.save(userService.getUserEvent());
//        verificationToken.get().setUser();
        return "valid";
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken == null) {
            return "invalid";
        }

        User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.get().setToken(UUID.randomUUID().toString());
        return verificationTokenRepository.save(verificationToken.get());
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.empty();
    }
}
