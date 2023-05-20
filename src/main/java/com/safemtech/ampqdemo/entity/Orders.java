package com.safemtech.ampqdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String date;
    private String status = "INITIALIZED";
    @OneToMany
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    public Orders(String date, String status, List<Product> products, Address shippingAddress) {
        this.date = date;
        this.status = status;
        this.products = products;
        this.shippingAddress = shippingAddress;
    }
}
   
        

