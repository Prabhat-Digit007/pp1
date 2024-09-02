package com.example.Projec1.services;

import com.example.Projec1.entity.Cart;
import com.example.Projec1.entity.CartItem;
import com.example.Projec1.entity.Product;
import com.example.Projec1.entity.User;
import com.example.Projec1.exception.customException.InsufficientStockException;
import com.example.Projec1.exception.customException.ResourceNotFoundException;
import com.example.Projec1.repo.ICartItemRepository;
import com.example.Projec1.repo.ICartRepository;
import com.example.Projec1.repo.IProductRepository;
import com.example.Projec1.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServices implements ICartServices {

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    IProductRepository bookStoreRepository;

    @Autowired
    IUserRepository usersRepository;

    @Autowired
    ICartItemRepository cartItemRepository;

    @Override
    public Cart getCartByUserId(Long userId) throws ResourceNotFoundException {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    public Cart addItemToCart(Long userId, Long bookId) throws ResourceNotFoundException {
        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            if (book.getStock() < 1) {
                throw new ResourceNotFoundException("Not enough stock available");
            }

            Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
                Cart newCart = new Cart();
                newCart.setUser(user);
                return newCart;
            });

            // Save the cart if it's new
            if (cart.getId() == null) {
                cart = cartRepository.save(cart);
            }

            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book)
                    .orElse(new CartItem(cart, book, 0, (Double.valueOf(String.valueOf(book.getPrice())))));
            cartItem.setUser(user); // Ensure the user is set

            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice(Double.valueOf(String.valueOf(book.getPrice())));

            book.setStock(book.getStock() - 1);

            // Add the cart item to the cart's items list
            if (!cart.getItems().contains(cartItem)) {
                cart.getItems().add(cartItem);
            }

            cartItemRepository.save(cartItem);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            cartRepository.save(cart);

            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }



    @Override
    public void removeItemFromCart(Long userId, Long bookId) throws ResourceNotFoundException {

        System.out.println("removeItemFromCart method called");
        System.out.println(userId+" and "+bookId);

        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            Cart cart = cartRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));


            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book)
                    .orElse(null);

            if (cartItem == null) {
                throw new ResourceNotFoundException("Item not found in cart");
            }


            int quantity = cartItem.getQuantity();
            cartItemRepository.delete(cartItem);

            book.setStock(book.getStock() + quantity);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            cartRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }



    public Cart increaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException, InsufficientStockException {
        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            Cart cart = cartRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book)
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

            if (book.getStock() < 1) {
                throw new InsufficientStockException("Not enough stock available");
            }

            cartItem.setQuantity(cartItem.getQuantity() + 1);
            //cartItem.setPrice(Double.valueOf(book.getPrice()) * cartItem.getQuantity());

            book.setStock(book.getStock() - 1);

            cartItemRepository.save(cartItem);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            cartRepository.save(cart);

            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }



    @Override
    public Cart decreaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException {
        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            Cart cart = cartRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book)
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                //cartItem.setPrice(Double.valueOf(book.getPrice()) * cartItem.getQuantity());

                book.setStock(book.getStock() + 1);

                cartItemRepository.save(cartItem);
                bookStoreRepository.save(book);

                cart.calculateTotalPrice();
                cartRepository.save(cart);
            } else {
                cartItemRepository.delete(cartItem);
                book.setStock(book.getStock() + 1);
                bookStoreRepository.save(book);

                cart.calculateTotalPrice();
                cartRepository.save(cart);
            }

            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }

}