package com.example.demo.controllers;

import com.example.demo.controllers.dtos.UserDtos.LogInDto;
import com.example.demo.controllers.dtos.UserDtos.SignUpRequest;
import com.example.demo.controllers.dtos.UserDtos.UserDto;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }


    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/userById")
    public ResponseEntity<UserDto> findById(@RequestParam("id") Integer userId) {
        try {
            UserDto usersCont = userService.findById(userId);
            return ResponseEntity.ok(usersCont);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<UserDto> updateUser(@RequestParam("firstname") String firstname, @RequestParam("email") String email) {
        try {
            UserDto updatedUser = userService.updateUser(firstname, email);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam("firstname") String firstname) {
        try {
            userService.deleteUser(firstname);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<UserDto> logIn(@RequestBody LogInDto logInDto) {
        try {
            UserDto usr = loginService.logIn(logInDto.getEmail(), logInDto.getPassword());
            return ResponseEntity.ok(usr);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signUpRequest){
        if(userService.hasCustomerWithEmail(signUpRequest.getEmail()))
            return new ResponseEntity<>("Customer already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = userService.createUser(signUpRequest);
        if(createdCustomerDto == null)
            return new ResponseEntity<>("Customer not created", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }
}
