package com.example.Projec1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Table(name = "CustomerDetails")
//public class UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Address> addresses = new ArrayList<>();
//
//}


//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "CustomerDetails")
//public class UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
//    private User user;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Address> addresses = new ArrayList<>();
//}


//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "CustomerDetails")
//public class UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
//    private User user;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Address> addresses = new ArrayList<>();
//}


//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "CustomerDetails")
//public class UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
//    private User user;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
//    private List<Address> addresses = new ArrayList<>();
//}


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CustomerDetails")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();
}
