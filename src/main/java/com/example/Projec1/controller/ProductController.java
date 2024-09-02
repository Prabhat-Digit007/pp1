
package com.example.Projec1.controller;

import com.example.Projec1.dao.ProductResponse;
import com.example.Projec1.entity.Product;
import com.example.Projec1.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user/admin")
public class ProductController {

    @Autowired
    IProductService orderService;

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping("/allOrder")
    public List<Product> getAllOrders(@RequestHeader("Authorization") String token) {
        String role = orderService.getRoleFromToken(token);
        if (role.equals("admin")) {
            return orderService.getAllOrders();
        }
        return null;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getOrderById(@RequestHeader("Authorization") String token,@PathVariable Long id) {
//        String role = orderService.getRoleFromToken(token);
//        if(role.equals("admin")){
//            Product order = orderService.getOrderById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//            return ResponseEntity.ok(order);
//        }
//        return null;
//    }

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping("/{id}")
    public ProductResponse getOrderById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        String role = orderService.getRoleFromToken(token);
        if (role.equals("admin")) {
            ProductResponse productResponse = orderService.showOrderById(id);
            return productResponse;
        }
        return new ProductResponse(HttpStatus.BAD_REQUEST,null,"You are not authorised");
//            if (productOptional.isPresent()) {
//                Product product = productOptional.get();
//                return ResponseEntity.ok(product);
//            }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }


    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/add/book")
    public Product addProduct(@RequestHeader("Authorization") String token,@Valid @RequestBody Product order) {
        String role = orderService.getRoleFromToken(token);
        if(role.equals("admin")){
            return orderService.createOrder(order);
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @PutMapping("/update/book/{product_id}")
    public ProductResponse updateProduct(@RequestHeader("Authorization") String token, @PathVariable Long product_id, @Valid @RequestBody Product orderDetails) {
        String role = orderService.getRoleFromToken(token);
        if(role.equals("admin")){
        Product updatedOrder = orderService.updateOrder(product_id, orderDetails);
            System.out.println(updatedOrder);
            if (updatedOrder==null){
                return new ProductResponse(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE,updatedOrder,"BookId is not present");
            }
            return new ProductResponse();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @DeleteMapping("/delete/book/{product_id}")
    public ProductResponse deleteProduct(@RequestHeader("Authorization") String token,@PathVariable Long product_id) {
        String role = orderService.getRoleFromToken(token);
        if(role.equals("admin")){
            ProductResponse productResponse = orderService.deleteOrder(product_id);
            return productResponse;
        }
        return new ProductResponse(HttpStatus.UNAUTHORIZED,null,"You are not authorised to access delete funtionality");
    }
}