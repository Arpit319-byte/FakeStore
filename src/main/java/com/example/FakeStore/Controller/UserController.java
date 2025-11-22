package com.example.FakeStore.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeStore.DTO.UserDTO;
import com.example.FakeStore.Service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private IUserService iUserService;

    public UserController(IUserService _iUserService) {
        log.info("Adding the dependency of IUserService for the UserController class");
        this.iUserService = _iUserService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Calling the Service to get all the Users from the FakeStoreAPI");
        return ResponseEntity.ok(iUserService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        log.info("Getting the User by Id for the id->" + userId);
        UserDTO user = iUserService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        log.info("Adding the User " + userDTO.toString() + " in the FakeStore");
        return ResponseEntity.ok(iUserService.addUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        log.info("Updating the User with id->" + id + " with updated value UserDTO->" + userDTO.toString());
        UserDTO existingUser = iUserService.getUserById(id);
        if (existingUser != null) {
            return ResponseEntity.ok(iUserService.updateUser(id, userDTO));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) {
        log.info("Deleting the User with the id->" + id);
        UserDTO existingUser = iUserService.getUserById(id);
        if (existingUser != null) {
            log.info("User with the id->" + id + " is present and deleting that User");
            return ResponseEntity.ok(iUserService.deleteUserById(id));
        }
        return ResponseEntity.notFound().build();
    }
}

