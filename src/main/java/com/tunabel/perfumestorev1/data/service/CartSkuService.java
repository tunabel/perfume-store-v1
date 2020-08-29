package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.CartSku;
import com.tunabel.perfumestorev1.data.repository.CartSkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartSkuService {

    @Autowired
    private CartSkuRepository cartSkuRepository;

    public void addNewCartSku(CartSku cartSku) {
        cartSkuRepository.save(cartSku);
    }

    public boolean updateCartSku(CartSku cartSku) {
        try {
            cartSkuRepository.save(cartSku);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CartSku findOne(int cartSkuId) {
        return cartSkuRepository.findOne(cartSkuId);
    }

    public boolean deleteCartSku(int cartSkuId) {
        try {
            cartSkuRepository.delete(cartSkuId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    public boolean deleteCartSkuList(List<CartSku> cartSkuList) {
        try {
            cartSkuRepository.delete(cartSkuList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CartSku findFirstCartSkuByCartIdAndSkuId(int cartId, int skuId) {
        try {
            return cartSkuRepository.findFirstCartSkuByCartIdAndSkuId(cartId,skuId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
