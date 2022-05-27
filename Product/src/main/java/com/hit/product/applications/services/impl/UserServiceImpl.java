package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.commons.ERole;
import com.hit.product.applications.exceptions.NotFoundException;
import com.hit.product.applications.repositories.PasswordResetTokenRepository;
import com.hit.product.applications.repositories.RoleRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.repositories.VerificationTokenRepository;
import com.hit.product.applications.services.UserService;
import com.hit.product.domain.dtos.UserDto;
import com.hit.product.domain.entities.PasswordResetToken;
import com.hit.product.domain.entities.User;
import com.hit.product.domain.entities.VerificationToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);
         return user.get();
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName(ERole.ROLE_USER).get()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);
        modelMapper.map(userDto, user);
        user.get().setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user.get());
    }

    @Override
    public TrueFalseResponse deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);
        userRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    @Override
    public User getUserEvent(User user) {
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }





    @Override
    public User getUserByToken(String token) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }



//    private User createOrUpdate(User user, UserDto userDto) {
//        modelMapper.map(userDto, user);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        return userRepository.save(user);
//    }




}
