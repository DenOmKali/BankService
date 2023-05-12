package org.geekhub.denis.service;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.UserEntity;
import org.geekhub.denis.enums.UserRole;
import org.geekhub.denis.model.*;
import org.geekhub.denis.repository.UserRepositoryImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Apilat Denis
 * Date :29.04.2023
 * Time :23:47
 * Project Name :gh-hw-denis-apilat
 */

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public static final Logger LOGGER = Logger.getGlobal();
    private final UserRepositoryImpl userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public CurrentUserModel currentUserModel;

    public JwtResponseModel register(RegisterRequestModel request) {
        UserEntity userEntity = UserEntity.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER.toString())
                .build();
        userRepository.saveUser(userEntity);
        String jwtToken = jwtService.generateToken(userEntity);
        currentUserModel = CurrentUserModel.builder()
                .email(userEntity.getEmail())
                .build();
        LOGGER.info("REGISTRATION: " + userEntity);
        return JwtResponseModel.builder()
                .token(jwtToken)
                .build();
    }

    public JwtResponseModel authenticate(AuthenticationRequestModel request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserEntity userEntity = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(userEntity);
        currentUserModel = CurrentUserModel.builder()
                .email(userEntity.getEmail())
                .build();
        LOGGER.info("AUTHENTICATION!");
        return JwtResponseModel.builder()
                .token(jwtToken)
                .build();
    }

    public CurrentUserModel getCurrentUser() {

        UserEntity userEntity = userRepository.findUserByEmail(currentUserModel.getEmail())
                .orElseThrow();
        return CurrentUserModel.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .phoneNumber(userEntity.getPhoneNumber())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();
    }

}
