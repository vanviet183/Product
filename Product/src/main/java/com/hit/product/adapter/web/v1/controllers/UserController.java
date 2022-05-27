package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.events.RegistrationCompleteEvent;
import com.hit.product.applications.repositories.VerificationTokenRepository;
import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domain.dtos.PasswordDto;
import com.hit.product.domain.dtos.UserDto;
import com.hit.product.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ApplicationEventPublisher publisher;

//    @GetMapping("")
//    public ResponseEntity<?> getUsers(@RequestParam Integer page) {
//        return ResponseEntity.ok().body(userService.getAccounts(page));
//    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        return this.resListSuccess(userService.getListUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        return this.resSuccess(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto, final HttpServletRequest request) {
        User user = userService.registerUser(userDto);
//        userService.save(user);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return this.resSuccess(user);
    }

    @PatchMapping("/{idAcc}")
    public ResponseEntity<?> updateUser(@PathVariable("idAcc") Long idAcc, @RequestBody UserDto userDto) {
        return this.resSuccess((userService.updateUser(idAcc, userDto)));
    }

    @DeleteMapping("/{idAcc}")
    public ResponseEntity<?> deleteUser(@PathVariable("idAcc") Long idAcc) {
        return ResponseEntity.ok().body(userService.deleteUserById(idAcc));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordDto passwordDto, HttpServletRequest request) {
        User user = userService.findUserByUsername(passwordDto.getUsername());
        String url = "";
        if(user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }
        return ResponseEntity.ok().body(url);
    }

    @PostMapping("/savePassword")
    public ResponseEntity<?> savePassword(@RequestParam String token, @RequestBody PasswordDto passwordDto) {
        String result = verificationTokenService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("Invalid token");
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()) {
            userService.changePassword(user.get(), passwordDto.getNewPassword());
            return ResponseEntity.ok().body("Password Reset Successfully");
        }

        return ResponseEntity.ok().body("Invalid token");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordDto passwordDto) {
        User user = userService.findUserByUsername(passwordDto.getUsername());
        if(!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            return ResponseEntity.ok().body("Invalid Old Password");
        }

        // Save new Password
        userService.changePassword(user, passwordDto.getNewPassword());
        return ResponseEntity.ok().body("Password Changed Successfully");
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl
                + "/savePassword?token="
                + token;
        emailSenderService.sendSimpleEmail(user.getEmail(), "Click the link to Reset your Password: " + url, "Reset Password VitApp Web");
        return url;
    }




    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }


}
