package com.example.demo.services;

import com.example.demo.controllers.dtos.UserDtos.UserDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserDto logIn(String email, String password) {
        UserEntity usr = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usr.getFirstname(), password));

        UserDto userDto = userMapper.toDto(usr);
        String jwt = jwtService.generateToken(userDto);
        userDto.setToken(jwt);
        return userDto;
    }
}
