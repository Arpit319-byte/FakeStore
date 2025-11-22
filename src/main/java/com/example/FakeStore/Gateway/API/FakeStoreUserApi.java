package com.example.FakeStore.Gateway.API;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.FakeStore.DTO.UserDTO;

@FeignClient(name = "fakeStoreUserApi", url = "https://fakestoreapi.com")
public interface FakeStoreUserApi {
    
    @GetMapping("/users")
    List<UserDTO> getAllUsers();

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @PostMapping("/users")
    UserDTO addUser(@RequestBody UserDTO user);

    @PutMapping("/users/{id}")
    UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user);

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") Long id);
}

