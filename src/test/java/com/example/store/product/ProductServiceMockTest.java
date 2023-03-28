package com.example.store.product;

import com.example.store.product.entity.Category;
import com.example.store.product.entity.Product;
import com.example.store.product.repository.ProductRepository;
import com.example.store.product.service.ProductService;
import com.example.store.product.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("5"))
                .price(Double.parseDouble("1230.99"))
                .status("Created")
                .createAt(new Date()).build();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertEquals(found.getName(), "computer");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        assertEquals(newStock.getStock().doubleValue(),13, "Mismo stock");
    }

}
