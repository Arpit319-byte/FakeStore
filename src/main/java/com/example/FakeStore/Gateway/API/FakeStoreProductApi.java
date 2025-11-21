package com.example.FakeStore.Gateway.API;

import java.util.List;

import com.example.FakeStore.DTO.ProductDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FakeStoreProductApi {
    
    @GET("/products")
    Call<List<ProductDTO>> getAllProduct();
    
}
