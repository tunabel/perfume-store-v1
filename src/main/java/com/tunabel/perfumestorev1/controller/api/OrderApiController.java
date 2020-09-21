package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.model.Order;
import com.tunabel.perfumestorev1.data.model.OrderSku;
import com.tunabel.perfumestorev1.data.service.OrderService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.BrandDto;
import com.tunabel.perfumestorev1.model.viewmodel.admin.AdminOrderSkuVM;
import com.tunabel.perfumestorev1.model.viewmodel.order.OrderSkuVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
public class OrderApiController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/update-status/{orderId}")
    public BaseApiResult updateBrand(@PathVariable int orderId,
                                     @RequestParam("orderStatus") int newStatus) {
        BaseApiResult result = new BaseApiResult();

        try {
            boolean isStatusUpdated = orderService.updateStatus(orderId, newStatus);

            if (isStatusUpdated) {
                result.setSuccessful(true);
                result.setMessage("Order Status updated successfully");
            } else {
                result.setSuccessful(false);
                result.setMessage("Order Status not updated");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("/order-sku/{orderId}")
    public BaseApiResult getOrderSkuDetails(@PathVariable int orderId) {
        DataApiResult result = new DataApiResult();

        try {
            AdminOrderSkuVM vm = new AdminOrderSkuVM();

            Order order = orderService.findOne(orderId);

            if (order != null) {
                vm.setUsername(order.getUsername());
                vm.setAddress(
                        "Receiver: " + order.getName()
                                + "\nAddress: " + order.getAddress()
                                + "\nPhone: " + order.getPhone()
                                + "\nEmail: " + order.getEmail()
                );
                vm.setOrderStatus(order.getStatus());
                vm.setOrderId(orderId);
                vm.setCreatedDate(order.getCreatedDate());
                vm.setTotalPrice(order.getTotalPrice());

                List<OrderSkuVM> orderSkuVMList = new ArrayList<>();

                for (OrderSku orderSku : order.getOrderSkuList()) {
                    OrderSkuVM orderSkuVM = new OrderSkuVM();

                    orderSkuVM.setSkuId(orderSku.getProductSKU().getId());
                    orderSkuVM.setMainImage(orderSku.getProductSKU().getImageURL());
                    orderSkuVM.setQuantity(orderSku.getQuantity());
                    orderSkuVM.setSkuName(orderSku.getProductSKU().getProduct().getBrand().getName() + " - " + orderSku.getProductSKU().getProduct().getName() + " - " + orderSku.getProductSKU().getName());
                    orderSkuVM.setPrice(orderSku.getPrice());
                    orderSkuVM.setUnitPrice(orderSku.getProductSKU().getPrice());
                    orderSkuVMList.add(orderSkuVM);
                }
                vm.setOrderSkuVMList(orderSkuVMList);
            }


            result.setSuccessful(true);
            result.setData(vm);


        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }


        return result;
    }
}
