package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Cart;
import com.tunabel.perfumestorev1.data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addNewCart(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart findOne(int cartId) {
        return cartRepository.findOne(cartId);
    }

    public boolean updateCart(Cart cart) {
        try {
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCart(int cartId) {
        try {
            cartRepository.delete(cartId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cart findFirstCartByGuid(String guid) {
        try {
            return cartRepository.findFirstCartByGuid(guid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cart findByUserName(String username) {
        try {
            return cartRepository.findByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
