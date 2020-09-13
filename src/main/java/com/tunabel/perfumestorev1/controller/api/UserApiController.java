package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.service.OrderService;
import com.tunabel.perfumestorev1.data.service.UserService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/switch-status/{userId}")
    public BaseApiResult updateBrand(@PathVariable int userId) {
        BaseApiResult result = new BaseApiResult();

        try {
            boolean isStatusUpdated = userService.switchStatus(userId);

            if (isStatusUpdated) {
                result.setSuccessful(true);
                result.setMessage("User Status updated successfully");
            } else {
                result.setSuccessful(false);
                result.setMessage("User Status not updated");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
