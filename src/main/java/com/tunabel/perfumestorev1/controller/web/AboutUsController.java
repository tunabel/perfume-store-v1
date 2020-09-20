package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import com.tunabel.perfumestorev1.data.model.blog.Tag;
import com.tunabel.perfumestorev1.data.service.BlogService;
import com.tunabel.perfumestorev1.data.service.TagService;
import com.tunabel.perfumestorev1.model.viewmodel.aboutus.AboutUsVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogDetailVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogPageVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.TagVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSearchVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = "/about-us")
public class AboutUsController extends BaseController {

    @GetMapping(value = "")
    public String showBlogPage(Model model,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);
        AboutUsVM vm = new AboutUsVM();

        HeaderMenuVM headerMenuVM = this.getHeaderMenuVM(cartQty, principal);
        headerMenuVM.setPageName("blog");
        vm.setHeaderMenuVM(headerMenuVM);

        model.addAttribute("vm", vm);
        return "about-us";
    }
}
