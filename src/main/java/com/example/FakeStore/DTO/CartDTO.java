package com.example.FakeStore.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CartDTO {
    private Integer id;
    private Integer userId;
    
    @JsonProperty("products")
    private List<ProductDTO> products;
}
