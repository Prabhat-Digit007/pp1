package com.example.Projec1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "CartItem")
//
//public class CartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id", nullable = false)
//    private Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @Column(name = "quantity", nullable = false)
//    private Integer quantity;
//
//}

//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "cart_item")
//public class CartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id", nullable = false)
//    @JsonIgnore
//    private Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false)
//    private Product product;
//
//    private Integer quantity = 0;
//    private Double price;
//
//    public CartItem(Cart cart, Product product, Integer quantity, Double price) {
//        this.cart = cart;
//        this.product = product;
//        this.quantity = quantity != null ? quantity : 0;
//        this.price = price;
//    }
//}


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Product book;

    private Integer quantity = 0;
    private Double price;

    public CartItem(Cart cart, Product book, Integer quantity, Double price) {
        this.cart = cart;
        this.book = book;
        this.quantity = quantity != null ? quantity : 0;
        this.price = price;
    }
}
