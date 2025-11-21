package com.example.FakeStore.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.FakeStore.DTO.ProductDTO;
import com.example.FakeStore.Service.IProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {


    private IProductService iProductService;

    public ProductController(IProductService _IProductService){
        this.iProductService=_IProductService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct() throws IOException{
        return ResponseEntity.ok(iProductService.getAllProduct());
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(){
        return ResponseEntity.ok(new ProductDTO());
    }


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok("Created succesfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@RequestBody ProductDTO productDTO,@RequestParam Long id){
           return ResponseEntity.ok("Updated Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@RequestParam Long id){
        return ResponseEntity.ok("Deleted Successfully");
    }

    
}
