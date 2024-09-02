package com.example.Projec1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@Table(name = "Address")
//@Entity
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String fullName;
//    private String mobileNumber;
//    private String addressLine1;
//    private String addressLine2;
//    private String landmark;
//    private Long pincode;
//    private String city;
//    private String state;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_details_id")
//    @JsonIgnore
//    private UserDetails userDetails;
//}

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "Address")
//@Entity
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String fullName;
//    private String mobileNumber;
//    private String addressLine1;
//    private String addressLine2;
//    private String landmark;
//    private Long pincode;
//    private String city;
//    private String state;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_details_id")
//    @JsonIgnore
//    private UserDetails userDetails;
//}


//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "Address")
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String fullName;
//    private String mobileNumber;
//    private String addressLine1;
//    private String addressLine2;
//    private String landmark;
//    private Long pincode;
//    private String city;
//    private String state;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_details_id")
//    @JsonIgnore
//    private UserDetails userDetails;
//}


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String mobileNumber;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private Long pincode;
    private String city;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id")
    @JsonIgnore
    private UserDetails userDetails;
}
