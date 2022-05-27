package com.hit.product.applications.services;

import com.hit.product.domain.entities.User;
import com.hit.product.domain.entities.VerificationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VerificationTokenService {

    void createVerificationToken(String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);
}
