package com.theironyard;

import javax.persistence.*;

/**
 * Created by vajrayogini on 3/9/16.
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String creditCard;
    @Column(nullable = false)
    String cvv;
    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer; //for each person associate a customer with it (creates customer_id)

//    public Purchase(String date, String creditCard, String cvv, String category) {
//        this.date = date;
//        this.creditCard = creditCard;
//        this.cvv = cvv;
//        this.category = category;
//    }

    public Purchase() {
    }
}
