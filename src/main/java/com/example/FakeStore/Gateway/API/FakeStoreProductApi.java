package com.example.FakeStore.Gateway.API;

import java.util.List;

import com.example.FakeStore.DTO.ProductDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FakeStoreProductApi {
    
    @GET("/products")
    Call<List<ProductDTO>> getAllProduct();

    @GET("/products/{id}")
    Call<ProductDTO> getProductById(@Path("id") Long id);

    @POST("/products")
    Call<ProductDTO> addProduct(@Body ProductDTO product);

    @PUT("/products/{id}")
    Call<ProductDTO> updateProduct(@Path("id") Long id, @Body ProductDTO product);

    @DELETE("/products/{id}")
    Call<Void> deleteProduct(@Path("id") Long id);
    
}
