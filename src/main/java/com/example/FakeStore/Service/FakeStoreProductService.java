package com.example.FakeStore.Service;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.FakeStore.DTO.ProductDTO;
import com.example.FakeStore.Gateway.IProductGateway;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FakeStoreProductService  implements IProductService{

    private final IProductGateway iProductGateway;

    public FakeStoreProductService(IProductGateway _iProductGateway){
        log.info("Adding the IProductGateway dependency for the FakeStoreProductService");
        this.iProductGateway=_iProductGateway;
    }

    @Override
    public List<ProductDTO> getAllProduct() throws IOException{
        log.info("Getting all the Product from the IProductGateway");
        return iProductGateway.getAllProduct();
    }

    @Override
    public ProductDTO getProductById(Long id) throws IOException {
        log.info("Getting product by id: {} from the IProductGateway", id);
        return iProductGateway.getProductById(id);
    }

    @Override
    public ProductDTO addProductDTO(ProductDTO productDTO) throws IOException {
        log.info("Adding product: {} to the IProductGateway", productDTO);
        return iProductGateway.addProduct(productDTO);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws IOException {
        log.info("Updating product with id: {} in the IProductGateway", id);
        return iProductGateway.updateProduct(id, productDTO);
    }

    @Override
    public Boolean deleteProductById(Long id) throws IOException {
        log.info("Deleting product with id: {} from the IProductGateway", id);
        return iProductGateway.deleteProductById(id);
    }
    
}
