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


    @Override
    public ProductDTO addProduct(ProductDTO productDTO) throws IOException {
        log.info("Calling the Api of the fakeStore to add product: {}", productDTO);
        var response = fakeStoreProductApi.addProduct(productDTO).execute();
        if (!response.isSuccessful()) {
            log.error("Failed to add product. Response code: {}", response.code());
            throw new IOException("Failed to add product: HTTP " + response.code());
        }
        return response.body();
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws IOException {
        log.info("Calling the Api of the fakeStore to update product with id: {}", id);
        var response = fakeStoreProductApi.updateProduct(id, productDTO).execute();
        if (!response.isSuccessful()) {
            log.error("Failed to update product with id: {}. Response code: {}", id, response.code());
            throw new IOException("Failed to update product: HTTP " + response.code());
        }
        return response.body();
    }

    @Override
    public Boolean deleteProductById(Long id) throws IOException {
        log.info("Calling the Api of the fakeStore to delete product with id: {}", id);
        var response = fakeStoreProductApi.deleteProduct(id).execute();
        if (!response.isSuccessful()) {
            log.error("Failed to delete product with id: {}. Response code: {}", id, response.code());
            throw new IOException("Failed to delete product: HTTP " + response.code());
        }
        return response.isSuccessful();
    }
    
}
