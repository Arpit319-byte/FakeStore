package com.example.FakeStore.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeStore.DTO.ProductDTO;
import com.example.FakeStore.Gateway.IProductGateway;


@Service
public class FakeStoreProductService  implements IProductService{

    private final IProductGateway iProductGateway;

    public FakeStoreProductService(IProductGateway _iProductGateway){
        this.iProductGateway=_iProductGateway;
    }

    @Override
    public List<ProductDTO> getAllProduct() throws IOException{
        return iProductGateway.getAllProduct();
    }
    
}
