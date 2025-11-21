package com.example.FakeStore.Gateway;

import java.io.IOException;
import java.util.List;

import com.example.FakeStore.DTO.ProductDTO;
import com.example.FakeStore.Gateway.API.FakeStoreProductApi;

public class FakeStoreProductGateway implements IProductGateway{

    private FakeStoreProductApi fakeStoreProductApi;

    public FakeStoreProductGateway(FakeStoreProductApi _FakeStoreProductApi){
        this.fakeStoreProductApi=_FakeStoreProductApi;
    }
    @Override
    public List<ProductDTO> getAllProduct() throws IOException {
        return fakeStoreProductApi.getAllProduct().execute().body();
    }
    
}
