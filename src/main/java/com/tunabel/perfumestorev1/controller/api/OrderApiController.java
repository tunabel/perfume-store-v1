package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.model.Order;
import com.tunabel.perfumestorev1.data.service.OrderService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
}
