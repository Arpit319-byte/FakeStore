package com.example.FakeStore.Controller;

import java.io.IOException;
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

import com.example.FakeStore.DTO.CartDTO;
import com.example.FakeStore.Service.ICartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    private ICartService iCartService;

    public CartController(ICartService _iCartService) throws IOException {
        log.info("Adding the dependency of ICartService for the CartController class");
        this.iCartService = _iCartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCart()  throws IOException {
        log.info("Calling the Service to get all the Carts from the FakeStoreAPI");
        return ResponseEntity.ok(iCartService.getAllCart());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long cartId) throws IOException {
        log.info("Getting the Cart by Id for the id->" + cartId);
        CartDTO cart = iCartService.getCartById(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cartDTO) throws IOException {
        log.info("Adding the Cart " + cartDTO.toString() + " in the FakeStore");
        return ResponseEntity.ok(iCartService.addCart(cartDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCartById(@RequestBody CartDTO cartDTO, @PathVariable Long id) throws IOException {
        log.info("Updating the Cart with id->" + id + " with updated value CartDTO->" + cartDTO.toString());
        CartDTO existingCart = iCartService.getCartById(id);
        if (existingCart != null) {
            return ResponseEntity.ok(iCartService.updateCartByID(id, cartDTO));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCartById(@PathVariable Long id)  throws IOException{
        log.info("Deleting the Cart with the id->" + id);
        CartDTO existingCart = iCartService.getCartById(id);
        if (existingCart != null) {
            log.info("Cart with the id->" + id + " is present and deleting that Cart");
            return ResponseEntity.ok(iCartService.deleteCardById(id));
        }
        return ResponseEntity.notFound().build();
    }
}
