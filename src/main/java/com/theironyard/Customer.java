package com.theironyard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by vajrayogini on 3/9/16.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String email;


    public Customer(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Customer() {
    }
}
