package com.java.products;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.java.products.model.Order;
import com.java.products.model.OrderItem;
import com.java.products.model.Product;
import com.java.products.repository.OrderRepository;
import com.java.products.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.stream.LongStream;

@SpringBootApplication
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

    @Bean
	CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.deleteAll();
            LongStream.range(1, 11)
                    .mapToObj(i -> {
                        Product p = new Product();
                        p.setName("Product " + i);
                        p.setPrice( i % 2 == 0 ? BigDecimal.TEN : new BigDecimal("7.00"));
                        return p;
                    })
                    .map(productRepository::save)
                    .forEach(System.out::println);

        };
    }
}
