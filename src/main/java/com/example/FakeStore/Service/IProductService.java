package com.example.FakeStore.Service;

import java.io.IOException;
import java.util.List;
import com.example.FakeStore.DTO.ProductDTO;

public interface IProductService {
    List<ProductDTO> getAllProduct() throws IOException;
    ProductDTO getProductById(Long id) throws IOException;
}
