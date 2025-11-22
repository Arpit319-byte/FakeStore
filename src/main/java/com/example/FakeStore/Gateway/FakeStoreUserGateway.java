package com.example.FakeStore.Gateway;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.FakeStore.DTO.UserDTO;
import com.example.FakeStore.Gateway.API.FakeStoreUserApi;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FakeStoreUserGateway implements IUserGateway {

    private final FakeStoreUserApi fakeStoreUserApi;

    public FakeStoreUserGateway(FakeStoreUserApi fakeStoreUserApi) {
        this.fakeStoreUserApi = fakeStoreUserApi;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Calling the Api of the fakeStore to fetch all users");
        try {
            return fakeStoreUserApi.getAllUsers();
        } catch (FeignException e) {
            log.error("Failed to fetch users. Response code: {}", e.status());
            throw new RuntimeException("Failed to fetch users: HTTP " + e.status(), e);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch users", e);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Calling the Api of the fakeStore to fetch user with id: {}", id);
        try {
            return fakeStoreUserApi.getUserById(id);
        } catch (FeignException.NotFound e) {
            log.warn("User with id: {} not found", id);
            return null;
        } catch (FeignException e) {
            log.error("Failed to fetch user with id: {}. Response code: {}", id, e.status());
            throw new RuntimeException("Failed to fetch user: HTTP " + e.status(), e);
        } catch (Exception e) {
            log.error("Error fetching user with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch user", e);
        }
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        log.info("Calling the Api of the fakeStore to add user: {}", userDTO);
        try {
            return fakeStoreUserApi.addUser(userDTO);
        } catch (FeignException e) {
            log.error("Failed to add user. Response code: {}", e.status());
            throw new RuntimeException("Failed to add user: HTTP " + e.status(), e);
        } catch (Exception e) {
            log.error("Error adding user: {}", e.getMessage());
            throw new RuntimeException("Failed to add user", e);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Calling the Api of the fakeStore to update user with id: {}", id);
        try {
            return fakeStoreUserApi.updateUser(id, userDTO);
        } catch (FeignException e) {
            log.error("Failed to update user with id: {}. Response code: {}", id, e.status());
            throw new RuntimeException("Failed to update user: HTTP " + e.status(), e);
        } catch (Exception e) {
            log.error("Error updating user with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @Override
    public Boolean deleteUserById(Long id) {
        log.info("Calling the Api of the fakeStore to delete user with id: {}", id);
        try {
            fakeStoreUserApi.deleteUser(id);
            return true;
        } catch (FeignException e) {
            log.error("Failed to delete user with id: {}. Response code: {}", id, e.status());
            throw new RuntimeException("Failed to delete user: HTTP " + e.status(), e);
        } catch (Exception e) {
            log.error("Error deleting user with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}

