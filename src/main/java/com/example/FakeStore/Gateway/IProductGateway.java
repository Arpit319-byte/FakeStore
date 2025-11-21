package com.example.FakeStore.Gateway;

import java.io.IOException;
import java.util.List;
import com.example.FakeStore.DTO.ProductDTO;

public interface IProductGateway {
   List<ProductDTO> getAllProduct() throws IOException;
   ProductDTO getProductById(Long id) throws IOException;
}
