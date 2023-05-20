package com.safemtech.ampqdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safemtech.ampqdemo.entity.Address;
import com.safemtech.ampqdemo.entity.Orders;
import com.safemtech.ampqdemo.entity.Product;
import com.safemtech.ampqdemo.repository.AddressRepository;
import com.safemtech.ampqdemo.repository.OrderRepository;
import com.safemtech.ampqdemo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@Slf4j
public class AmpqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmpqDemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(OrderRepository orderRepository, AddressRepository addressRepository, ProductRepository productRepository){
        return args -> {
            Address address = new Address("124 Queens road", "Apt 6518", "Austin", "TX");
            addressRepository.save(address);
            Product product1 = new Product("SAMSUNG Z-FOLD-4", 1270.99, "Latest Samsung release");
            Product product2 = new Product("APPLE IPHONE-14", 1354.84, "Apple Iphone 14");
            List<Product> products = List.of(product1, product2);
            productRepository.saveAll(products);
            Orders order = new Orders(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), "INITIATED",
                    products, address);

            log.info(new ObjectMapper().writeValueAsString(order)) ;

            orderRepository.save(order);
        };
    }
}
