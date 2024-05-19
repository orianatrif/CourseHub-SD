package com.example.demo.services;

import com.example.demo.controllers.dtos.UserDtos.SignUpRequest;
import com.example.demo.controllers.dtos.UserDtos.UserDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByFirstname(username);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserDto addUser(UserDto userDto) {
        UserEntity user = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }

    public UserDto findById(Integer userId) {
        return userMapper.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + userId + "not found!")));
    }

    public UserDto updateUser(String name, String password) {
        UserEntity usr = (UserEntity) userRepository.findByFirstname(name);

        usr.setPassword(password);
        userRepository.save(usr);
        return userMapper.toDto(usr);
    }

    public void deleteUser(String firstName) {
        UserEntity usr = (UserEntity) userRepository.findByFirstname(firstName);

        if (usr != null)
            userRepository.delete(usr);
        else {
            throw new RuntimeException("User with first name " + firstName + "not found");
        }
    }

    public UserDto createUser(SignUpRequest signUpRequest) {
        UserEntity usr = new UserEntity();
        usr.setFirstname(signUpRequest.getFirstName());
        usr.setLastname(signUpRequest.getLastName());
        usr.setEmail(signUpRequest.getEmail());
        usr.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        usr.setRole(UserRole.USER);
        UserEntity createdUser = userRepository.save(usr);
        return userMapper.toDto(createdUser);
    }

    public boolean hasCustomerWithEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }



}




