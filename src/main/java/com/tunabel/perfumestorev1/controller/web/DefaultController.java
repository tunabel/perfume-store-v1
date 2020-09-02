package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.constant.StatusRegisterUserEnum;
import com.tunabel.perfumestorev1.data.model.User;
import com.tunabel.perfumestorev1.data.service.CartSkuService;
import com.tunabel.perfumestorev1.data.service.UserService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class DefaultController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartSkuService cartSkuService;

    @GetMapping("/login")
    public String login(final Principal principal) {

        if (principal == null) {
            return "/login";
        }
        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, final Principal principal) {
         Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @RequestMapping(path = "/register-user", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("user") User user) {

        StatusRegisterUserEnum status = userService.registerNewUser(user);

        switch (status) {
            case Existed_Email:
                return "redirect:/register?erremail";
            case Existed_Username:
                return "redirect:/register?erruser";
            case Error_OnSystem:
                return "redirect:/register?errsys";
        }

        return "redirect:/login?success";
    }

    @GetMapping("/api/cart-sku/delete/{cartSkuId}")
    public String deleteCartSku(@PathVariable int cartSkuId) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(cartSkuService.deleteCartSku(cartSkuId) == true) {
                result.setMessage("Delete success");
                result.setSuccessful(true);
                return "redirect:/cart";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccessful(false);
        result.setMessage("Delete failure!");
        return "redirect:/cart";
    }
}
