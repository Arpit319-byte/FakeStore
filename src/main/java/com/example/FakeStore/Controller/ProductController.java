package com.example.FakeStore.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.FakeStore.DTO.ProductDTO;
import com.example.FakeStore.Service.IProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private IProductService iProductService;

    public ProductController(IProductService _IProductService){
        log.info("Adding the dependency of IProductService for the ProductController class");
        this.iProductService=_IProductService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct() throws IOException{
        log.info("Calling the Service to get all the Products from the FakeStoreAPI");
        return ResponseEntity.ok(iProductService.getAllProduct());
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) throws IOException{
        log.info("Getting the Product by Id for the id->"+productId);
        return ResponseEntity.ok(iProductService.getProductById(productId));
    }


    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) throws IOException{
        log.info("Adding the Product "+productDTO.toString()+" in the FakeStore");
        return ResponseEntity.ok(iProductService.addProductDTO(productDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@RequestBody ProductDTO productDTO,@PathVariable Long id) throws IOException{
           log.info("Updating the Product with id->"+id+" with updated value ProductDTO->"+productDTO.toString());
           ProductDTO existingProduct = iProductService.getProductById(id);
           if(existingProduct != null){
               return ResponseEntity.ok(iProductService.updateProduct(id, productDTO));
           }
           return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Long id)throws IOException{
        log.info("Deleting the Product with the id->"+id);
        if(iProductService.getProductById(id)!=null){
            log.info(" Product with the id->"+id+" is present and deleting that Product");
           return ResponseEntity.ok(iProductService.deleteProductById(id));
        }
        return ResponseEntity.notFound().build();
    }

    
}
