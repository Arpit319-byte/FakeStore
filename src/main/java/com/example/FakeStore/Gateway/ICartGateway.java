package com.example.FakeStore.Gateway;

import java.util.List;
import com.example.FakeStore.DTO.CartDTO;

public interface ICartGateway {
    List<CartDTO> getAllCart();
    CartDTO getCartById(Long id);
    CartDTO addCart(CartDTO cartDTO);
    CartDTO updateCart(Long id, CartDTO cartDTO);
    Boolean deleteCartById(Long id);
}

