package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.User;
import com.tunabel.perfumestorev1.data.service.UserService;
import com.tunabel.perfumestorev1.model.viewmodel.user.ChangePasswordVM;
import com.tunabel.perfumestorev1.model.viewmodel.user.UserDetailVM;
import com.tunabel.perfumestorev1.model.viewmodel.user.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/detail")
    public String getUserDetail(Model model) {
        UserDetailVM vm = new UserDetailVM();
        UserVM userVM = new UserVM();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User userEntity = userService.findUserByUsername(username);

        userVM.setAddress(userEntity.getAddress());
        userVM.setAvatar(userEntity.getAvatarURL());
        userVM.setEmail(userEntity.getEmail());
        userVM.setGender(userEntity.getGender());
        userVM.setName(userEntity.getName());
        userVM.setPhoneNumber(userEntity.getPhone());


        model.addAttribute("vm", vm);
        model.addAttribute("user", userVM);

        return "/user-detail";
    }

    @PostMapping("/update")
    public String getUserDetail(@Valid @ModelAttribute("user") UserVM user) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User userEntity = userService.findUserByUsername(username);

            userEntity.setAddress(user.getAddress());
            userEntity.setAvatarURL(user.getAvatar());
            userEntity.setEmail(user.getEmail());
            userEntity.setPhone(user.getPhoneNumber());
            userEntity.setGender(user.getGender());
            userEntity.setName(user.getName());
            userService.updateUser(userEntity);

            return "redirect:/user/detail?updateSuccess";
        } catch (Exception ex ) {
            ex.getMessage();
        }
        return "redirect:/user/detail?updateFailure";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        ChangePasswordVM changePasswordVM = new ChangePasswordVM();

        model.addAttribute("changePassword", changePasswordVM);

        return "/change-password";
    }

    @PostMapping("/change-password")
    public String changePassWordPost(@Valid @ModelAttribute("changePassword") ChangePasswordVM changePasswordVM) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        String currentPassHash = user.getPassword();

        if (passwordEncoder.matches(changePasswordVM.getNewPassword(), currentPassHash))  {
            if (changePasswordVM.getConfirmPassword().equals(changePasswordVM.getNewPassword())) {
                user.setPassword(changePasswordVM.getConfirmPassword());
                userService.updateUser(user);

                return "redirect:/user/change-password?success";
            }
        }
        return "redirect:/user/change-password?fail";

    }
}
