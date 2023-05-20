package com.safemtech.ampqdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String line1;

    private String line2;

    private String city;

    private String state;

    public Address(String line1, String line2, String city, String state) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
    }
}
