package com.example.FakeStore.Service;

import java.io.IOException;
import java.util.List;
import com.example.FakeStore.DTO.CartDTO;

public interface ICartService {
    List<CartDTO> getAllCart() throws IOException;
    CartDTO getCartById(Long id) throws IOException;
    CartDTO addCart(CartDTO cartDTO) throws IOException;
    CartDTO updateCartByID(Long id,CartDTO cartDTO) throws IOException;
    Boolean deleteCardById(Long id) throws IOException;
}
