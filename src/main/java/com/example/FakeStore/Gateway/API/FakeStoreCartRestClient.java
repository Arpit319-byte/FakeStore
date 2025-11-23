package com.example.FakeStore.Gateway.API;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.example.FakeStore.DTO.CartDTO;
import com.example.FakeStore.Gateway.ICartGateway;

import lombok.extern.slf4j.Slf4j;

@Component("fakeStoreCartRestClient")
@Slf4j
public class FakeStoreCartRestClient implements ICartGateway {

    private final RestClient restClient;
    private static final String BASE_URL = "https://fakestoreapi.com/carts";

    public FakeStoreCartRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<CartDTO> getAllCart() {
        log.info("Calling the Api of the fakeStore to fetch all carts");
        try {
            return restClient.get()
                    .uri(BASE_URL)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<List<CartDTO>>() {})
                    .getBody();
        } catch (HttpClientErrorException e) {
            log.error("Failed to fetch carts. Response code: {}", e.getStatusCode());
            throw new RuntimeException("Failed to fetch carts: HTTP " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            log.error("Error fetching carts: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch carts", e);
        }
    }

    @Override
    public CartDTO getCartById(Long id) {
        log.info("Calling the Api of the fakeStore to fetch cart with id: {}", id);
        try {
            return restClient.get()
                    .uri(BASE_URL + "/" + id)
                    .retrieve()
                    .body(CartDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Cart with id: {} not found", id);
            return null;
        } catch (HttpClientErrorException e) {
            log.error("Failed to fetch cart with id: {}. Response code: {}", id, e.getStatusCode());
            throw new RuntimeException("Failed to fetch cart: HTTP " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            log.error("Error fetching cart with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch cart", e);
        }
    }

    @Override
    public CartDTO addCart(CartDTO cartDTO) {
        log.info("Calling the Api of the fakeStore to add cart: {}", cartDTO);
        try {
            return restClient.post()
                    .uri(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(cartDTO)
                    .retrieve()
                    .body(CartDTO.class);
        } catch (HttpClientErrorException e) {
            log.error("Failed to add cart. Response code: {}", e.getStatusCode());
            throw new RuntimeException("Failed to add cart: HTTP " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            log.error("Error adding cart: {}", e.getMessage());
            throw new RuntimeException("Failed to add cart", e);
        }
    }

    @Override
    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        log.info("Calling the Api of the fakeStore to update cart with id: {}", id);
        try {
            String url = BASE_URL + "/" + id;
            restClient.put()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(cartDTO)
                    .retrieve()
                    .toBodilessEntity();
            // After PUT, fetch the updated cart to return it
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(CartDTO.class);
        } catch (HttpClientErrorException e) {
            log.error("Failed to update cart with id: {}. Response code: {}", id, e.getStatusCode());
            throw new RuntimeException("Failed to update cart: HTTP " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            log.error("Error updating cart with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update cart", e);
        }
    }

    @Override
    public Boolean deleteCartById(Long id) {
        log.info("Calling the Api of the fakeStore to delete cart with id: {}", id);
        try {
            restClient.delete()
                    .uri(BASE_URL + "/" + id)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (HttpClientErrorException e) {
            log.error("Failed to delete cart with id: {}. Response code: {}", id, e.getStatusCode());
            throw new RuntimeException("Failed to delete cart: HTTP " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            log.error("Error deleting cart with id: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete cart", e);
        }
    }
    
}
