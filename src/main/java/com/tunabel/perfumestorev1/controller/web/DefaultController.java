package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.constant.StatusRegisterUserEnum;
import com.tunabel.perfumestorev1.data.model.User;
import com.tunabel.perfumestorev1.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class DefaultController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpServletResponse response,
                        HttpServletRequest request,
                        final Principal principal) {

        if (principal == null) {

            return "/login";
        }
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
}
