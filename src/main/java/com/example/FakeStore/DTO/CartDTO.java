package com.example.FakeStore.DTO;

import java.util.List;

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
public class FakeStoreResponseDTO {
    private String status;
    private String message;
    private List<ProductDTO> product;
}
