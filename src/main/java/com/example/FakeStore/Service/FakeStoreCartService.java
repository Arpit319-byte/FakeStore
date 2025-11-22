package com.example.FakeStore.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeStore.DTO.CartDTO;
import com.example.FakeStore.Gateway.ICartGateway;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FakeStoreCartService implements ICartService {

    private final ICartGateway iCartGateway;

    public FakeStoreCartService(ICartGateway _iCartGateway) {
        log.info("Adding the ICartGateway dependency for the FakeStoreCartService");
        this.iCartGateway = _iCartGateway;
    }

    @Override
    public List<CartDTO> getAllCart() {
        log.info("Getting all carts from the ICartGateway");
        return iCartGateway.getAllCart();
    }

    @Override
    public CartDTO getCartById(Long id) {
        log.info("Getting cart by id: {} from the ICartGateway", id);
        return iCartGateway.getCartById(id);
    }

    @Override
    public CartDTO addCart(CartDTO cartDTO) {
        log.info("Adding cart: {} to the ICartGateway", cartDTO);
        return iCartGateway.addCart(cartDTO);
    }

    @Override
    public CartDTO updateCartByID(Long id, CartDTO cartDTO) {
        log.info("Updating cart with id: {} in the ICartGateway", id);
        return iCartGateway.updateCart(id, cartDTO);
    }

    @Override
    public Boolean deleteCardById(Long id) {
        log.info("Deleting cart with id: {} from the ICartGateway", id);
        return iCartGateway.deleteCartById(id);
    }
}

