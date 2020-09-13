package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Order;
import com.tunabel.perfumestorev1.data.model.OrderSku;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductSKUService productSKUService;

    public void add(Order order) {
        orderRepository.save(order);
    }

    public Order findOne(int orderId) {
        return orderRepository.findOne(orderId);
    }

    public List<Order> findOrderByGuidOrUserName(String guid, String userName) {
        return orderRepository.findOrderByGuidOrUserName(guid, userName);
    }

    public Page<Order> getPage(Pageable pageable) {
        return orderRepository.getPage(pageable);
    }

    @Transactional
    public boolean updateStatus(int orderId, int newStatus) {
        Order order = findOne(orderId);
        int currStatus = order.getStatus();
        List<OrderSku> orderSkuList = order.getOrderSkuList();
        if (order != null) {

            if (currStatus == 0 && newStatus == 2) {
                for (OrderSku orderSku : orderSkuList) {
                    ProductSku sku = orderSku.getProductSKU();
                    sku.setQuantity(sku.getQuantity() - orderSku.getQuantity());
                    productSKUService.add(sku);
                }
            }
            order.setStatus(newStatus);
            order.setCreatedDate(new Date());
            add(order);
            return true;
        }
        return false;

    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findOrderByGuidOrUserName(null, username);
    }
}
