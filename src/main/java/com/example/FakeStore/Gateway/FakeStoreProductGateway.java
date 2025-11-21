package com.example.FakeStore.Gateway;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.FakeStore.DTO.ProductDTO;
import com.example.FakeStore.Gateway.API.FakeStoreProductApi;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FakeStoreProductGateway implements IProductGateway{

    private FakeStoreProductApi fakeStoreProductApi;

    public FakeStoreProductGateway(FakeStoreProductApi _FakeStoreProductApi){
        this.fakeStoreProductApi=_FakeStoreProductApi;
    }
    @Override
    public List<ProductDTO> getAllProduct() throws IOException {
        log.info("Calling the Api of the fakeStore to fectch all the products");
        return fakeStoreProductApi.getAllProduct().execute().body();
    }


    @Override
    public ProductDTO getProductById(Long id) throws IOException {
        log.info("Calling the Api of the fakeStore to fetch product with id: {}", id);
        var response = fakeStoreProductApi.getProductById(id).execute();
        if (!response.isSuccessful()) {
            log.error("Failed to fetch product with id: {}. Response code: {}", id, response.code());
            throw new IOException("Failed to fetch product: HTTP " + response.code());
        }
        ProductDTO product = response.body();
        if (product == null) {
            log.warn("Product with id: {} not found (null response)", id);
        }
        return product;
    }
    
}
