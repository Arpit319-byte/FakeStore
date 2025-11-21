package com.example.FakeStore.Gateway.API;

import java.util.List;

import com.example.FakeStore.DTO.ProductDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeStoreProductApi {
    
    @GET("/products")
    Call<List<ProductDTO>> getAllProduct();

    @GET("/products/{id}")
    Call<ProductDTO> getProductById(@Path("id") Long id);
    
}
