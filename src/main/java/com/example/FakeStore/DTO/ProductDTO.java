package com.example.FakeStore.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {

    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    
}
