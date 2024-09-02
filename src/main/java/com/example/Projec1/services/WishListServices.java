package com.example.Projec1.services;

import com.example.Projec1.entity.Wishlist;
import com.example.Projec1.entity.WishlistItem;
import com.example.Projec1.entity.Product;
import com.example.Projec1.entity.User;
import com.example.Projec1.exception.customException.InsufficientStockException;
import com.example.Projec1.exception.customException.ResourceNotFoundException;
import com.example.Projec1.repo.IWishListItemRepository;
import com.example.Projec1.repo.IWishListRepository;
import com.example.Projec1.repo.IProductRepository;
import com.example.Projec1.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//public class WishListServices implements IWishServices{
//    @Autowired
//    IWishListRepository wishListRepository;
//
//    @Autowired
//    IProductRepository bookStoreRepository;
//
//    @Autowired
//    IUserRepository usersRepository;
//
//    @Autowired
//    IWishListItemRepository wishListItemRepository;
//
//    public Wishlist getWishlistByUserId(Long userId) throws ResourceNotFoundException {
//        return wishListRepository.findByUserId(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));
//    }
//
//    public Wishlist addItemToWishlist(Long userId, Long bookId) throws ResourceNotFoundException {
//        Optional<User> userOptional = usersRepository.findById(userId);
//        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);
//
//        if (userOptional.isPresent() && bookOptional.isPresent()) {
//            User user = userOptional.get();
//            Product book = bookOptional.get();
//
//            if (book.getStock() < 1) {
//                throw new ResourceNotFoundException("Not enough stock available");
//            }
//
//            Wishlist cart = wishListRepository.findByUser(user).orElseGet(() -> {
//                Wishlist newWishlist = new Wishlist();
//                newWishlist.setUser(user);
//                return newWishlist;
//            });
//
//            // Save the cart if it's new
//            if (cart.getId() == null) {
//                cart = wishListRepository.save(cart);
//            }
//
//            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
//                    .orElse(new WishlistItem(cart, book, 0, (Double.valueOf(String.valueOf(book.getPrice())))));
//            cartItem.setUser(user); // Ensure the user is set
//
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
//            cartItem.setPrice(Double.valueOf(String.valueOf(book.getPrice())));
//
//            book.setStock(book.getStock() - 1);
//
//            // Add the cart item to the cart's items list
//            if (!cart.getItems().contains(cartItem)) {
//                cart.getItems().add(cartItem);
//            }
//
//            wishListItemRepository.save(cartItem);
//            bookStoreRepository.save(book);
//
//            cart.calculateTotalPrice();
//            wishListRepository.save(cart);
//
//            return cart;
//        } else {
//            throw new ResourceNotFoundException("User or Book not found");
//        }
//    }
//
//    public void removeItemFromWishlist(Long userId, Long bookId) throws ResourceNotFoundException {
//
//        System.out.println("removeItemFromWishlist method called");
//        System.out.println(userId+" and "+bookId);
//
//        Optional<User> userOptional = usersRepository.findById(userId);
//        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);
//
//        if (userOptional.isPresent() && bookOptional.isPresent()) {
//            User user = userOptional.get();
//            Product book = bookOptional.get();
//
//            Wishlist cart = wishListRepository.findByUser(user)
//                    .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));
//
//
//            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
//                    .orElse(null);
//
//            if (cartItem == null) {
//                throw new ResourceNotFoundException("Item not found in cart");
//            }
//
//
//            int quantity = cartItem.getQuantity();
//            wishListItemRepository.delete(cartItem);
//
//            book.setStock(book.getStock() + quantity);
//            bookStoreRepository.save(book);
//
//            cart.calculateTotalPrice();
//            wishListRepository.save(cart);
//        } else {
//            throw new ResourceNotFoundException("User or Book not found");
//        }
//    }
//
//
//
//    public Wishlist increaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException, InsufficientStockException {
//        Optional<User> userOptional = usersRepository.findById(userId);
//        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);
//
//        if (userOptional.isPresent() && bookOptional.isPresent()) {
//            User user = userOptional.get();
//            Product book = bookOptional.get();
//
//            Wishlist cart = wishListRepository.findByUser(user)
//                    .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));
//
//            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
//                    .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));
//
//            if (book.getStock() < 1) {
//                throw new InsufficientStockException("Not enough stock available");
//            }
//
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
//            //cartItem.setPrice(Double.valueOf(book.getPrice()) * cartItem.getQuantity());
//
//            book.setStock(book.getStock() - 1);
//
//            wishListItemRepository.save(cartItem);
//            bookStoreRepository.save(book);
//
//            cart.calculateTotalPrice();
//            wishListRepository.save(cart);
//
//            return cart;
//        } else {
//            throw new ResourceNotFoundException("User or Book not found");
//        }
//    }
//
//
//
//    @Override
//    public Wishlist decreaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException {
//        Optional<User> userOptional = usersRepository.findById(userId);
//        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);
//
//        if (userOptional.isPresent() && bookOptional.isPresent()) {
//            User user = userOptional.get();
//            Product book = bookOptional.get();
//
//            Wishlist cart = wishListRepository.findByUser(user)
//                    .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));
//
//            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
//                    .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));
//
//            if (cartItem.getQuantity() > 1) {
//                cartItem.setQuantity(cartItem.getQuantity() - 1);
//                //cartItem.setPrice(Double.valueOf(book.getPrice()) * cartItem.getQuantity());
//
//                book.setStock(book.getStock() + 1);
//
//                wishListItemRepository.save(cartItem);
//                bookStoreRepository.save(book);
//
//                cart.calculateTotalPrice();
//                wishListRepository.save(cart);
//            } else {
//                wishListItemRepository.delete(cartItem);
//                book.setStock(book.getStock() + 1);
//                bookStoreRepository.save(book);
//
//                cart.calculateTotalPrice();
//                wishListRepository.save(cart);
//            }
//
//            return cart;
//        } else {
//            throw new ResourceNotFoundException("User or Book not found");
//        }
//    }
//}


@Service
public class WishListServices implements IWishServices {

    @Autowired
    private IWishListRepository wishListRepository;

    @Autowired
    private IProductRepository bookStoreRepository;

    @Autowired
    private IUserRepository usersRepository;

    @Autowired
    private IWishListItemRepository wishListItemRepository;

    @Override
    public Wishlist getWishlistByUserId(Long userId) throws ResourceNotFoundException {
        return wishListRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));
    }

    @Override
    public Wishlist addItemToWishlist(Long userId, Long bookId) throws ResourceNotFoundException {
        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            if (book.getStock() < 1) {
                throw new ResourceNotFoundException("Not enough stock available");
            }

            Wishlist cart = wishListRepository.findByUser(user).orElseGet(() -> {
                Wishlist newWishlist = new Wishlist();
                newWishlist.setUser(user);
                return newWishlist;
            });

            // Save the cart if it's new
            if (cart.getId() == null) {
                cart = wishListRepository.save(cart);
            }

            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
                    .orElse(new WishlistItem(cart, book, 0, book.getPrice()));

            cartItem.setUser(user); // Ensure the user is set
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice(book.getPrice());
            book.setStock(book.getStock() - 1);

            // Add the cart item to the cart's items list
            if (!cart.getWishlistItems().contains(cartItem)) {
                cart.getWishlistItems().add(cartItem);
            }

            wishListItemRepository.save(cartItem);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            wishListRepository.save(cart);
            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }

    @Override
    public void removeItemFromWishlist(Long userId, Long bookId) throws ResourceNotFoundException {
        System.out.println("removeItemFromWishlist method called");
        System.out.println(userId + " and " + bookId);

        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            Wishlist cart = wishListRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));

            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
                    .orElse(null);

            if (cartItem == null) {
                throw new ResourceNotFoundException("Item not found in cart");
            }

            int quantity = cartItem.getQuantity();
            wishListItemRepository.delete(cartItem);

            book.setStock(book.getStock() + quantity);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            wishListRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }
    public Wishlist increaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException, InsufficientStockException {
        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            Wishlist cart = wishListRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));

            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

            if (book.getStock() < 1) {
                throw new InsufficientStockException("Not enough stock available");
            }

            cartItem.setQuantity(cartItem.getQuantity() + 1);
            //cartItem.setPrice(Double.valueOf(book.getPrice()) * cartItem.getQuantity());

            book.setStock(book.getStock() - 1);

            wishListItemRepository.save(cartItem);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            wishListRepository.save(cart);

            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }



    @Override
    public Wishlist decreaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException {
        Optional<User> userOptional = usersRepository.findById(userId);
        Optional<Product> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            User user = userOptional.get();
            Product book = bookOptional.get();

            Wishlist cart = wishListRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));

            WishlistItem cartItem = wishListItemRepository.findByWishlistAndBook(cart, book)
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                //cartItem.setPrice(Double.valueOf(book.getPrice()) * cartItem.getQuantity());

                book.setStock(book.getStock() + 1);

                wishListItemRepository.save(cartItem);
                bookStoreRepository.save(book);

                cart.calculateTotalPrice();
                wishListRepository.save(cart);
            } else {
                wishListItemRepository.delete(cartItem);
                book.setStock(book.getStock() + 1);
                bookStoreRepository.save(book);

                cart.calculateTotalPrice();
                wishListRepository.save(cart);
            }

            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }
}

