package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domain.dtos.UserDto;
import com.hit.product.domain.entities.User;
import com.hit.product.domain.entities.VerificationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getListUser();

    User getUserById(Long id);

    User findUserByEmail(String email);

    User registerUser(UserDto userDto);

    User updateUser(Long id, UserDto userDto);

    TrueFalseResponse deleteUserById(Long id);

    User getUserEvent(User user);

    User save(User user);

    void saveVerificationTokenForUser(String token, User user);

    void createPasswordResetTokenForUser(User user, String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    User findUserByUsername(String username);

    boolean checkIfValidOldPassword(User user, String oldPassword);


    User getUserByToken(String token);
}
