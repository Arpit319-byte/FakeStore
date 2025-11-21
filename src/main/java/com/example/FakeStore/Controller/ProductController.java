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
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        log.info("Adding the Product "+productDTO.toString()+" in the FakeStore");
        return ResponseEntity.ok("Created succesfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@RequestBody ProductDTO productDTO,@PathVariable Long id){
           log.info("Updating the Product with id->"+id+" with updated value ProductDTO->"+productDTO.toString());
           return ResponseEntity.ok("Updated Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        log.info("Deleting the Product with the id->"+id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    
}
