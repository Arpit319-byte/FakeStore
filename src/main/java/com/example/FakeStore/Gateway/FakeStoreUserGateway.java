package com.example.FakeStore.Gateway;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.example.FakeStore.DTO.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FakeStoreUserGateway implements IUserGateway {

    private final WebClient webClient;
    private static final String USERS_ENDPOINT = "/users";

    public FakeStoreUserGateway(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Calling the Api of the fakeStore to fetch all users");
        try {
            return webClient.get()
                    .uri(USERS_ENDPOINT)
                    .retrieve()
                    .bodyToFlux(UserDTO.class)
                    .collectList()
                    .block(Duration.ofSeconds(30)); // Blocking with 30 second timeout
        } catch (WebClientResponseException e) {
            log.error("Failed to fetch users. Response code: {}", e.getStatusCode());
            throw new RuntimeException("Failed to fetch users: HTTP " + e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch users", e);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Calling the Api of the fakeStore to fetch user with id: {}", id);
        try {
            return webClient.get()
                    .uri(USERS_ENDPOINT + "/{id}", id)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block(Duration.ofSeconds(30)); // Blocking with 30 second timeout
        } catch (WebClientResponseException e) {
            log.error("Failed to fetch user with id: {}. Response code: {}", id, e.getStatusCode());
            if (e.getStatusCode().value() == 404) {
                log.warn("User with id: {} not found", id);
                return null;
            }
            throw new RuntimeException("Failed to fetch user: HTTP " + e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("Error fetching user with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch user", e);
        }
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        log.info("Calling the Api of the fakeStore to add user: {}", userDTO);
        try {
            return webClient.post()
                    .uri(USERS_ENDPOINT)
                    .bodyValue(userDTO)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block(Duration.ofSeconds(30)); // Blocking with 30 second timeout
        } catch (WebClientResponseException e) {
            log.error("Failed to add user. Response code: {}", e.getStatusCode());
            throw new RuntimeException("Failed to add user: HTTP " + e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("Error adding user: {}", e.getMessage());
            throw new RuntimeException("Failed to add user", e);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Calling the Api of the fakeStore to update user with id: {}", id);
        try {
            return webClient.put()
                    .uri(USERS_ENDPOINT + "/{id}", id)
                    .bodyValue(userDTO)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block(Duration.ofSeconds(30)); // Blocking with 30 second timeout
        } catch (WebClientResponseException e) {
            log.error("Failed to update user with id: {}. Response code: {}", id, e.getStatusCode());
            throw new RuntimeException("Failed to update user: HTTP " + e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("Error updating user with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @Override
    public Boolean deleteUserById(Long id) {
        log.info("Calling the Api of the fakeStore to delete user with id: {}", id);
        try {
            webClient.delete()
                    .uri(USERS_ENDPOINT + "/{id}", id)
                    .retrieve()
                    .toBodilessEntity()
                    .block(Duration.ofSeconds(30)); // Blocking with 30 second timeout
            return true;
        } catch (WebClientResponseException e) {
            log.error("Failed to delete user with id: {}. Response code: {}", id, e.getStatusCode());
            throw new RuntimeException("Failed to delete user: HTTP " + e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("Error deleting user with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}

