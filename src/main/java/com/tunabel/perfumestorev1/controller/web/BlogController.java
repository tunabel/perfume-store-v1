package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import com.tunabel.perfumestorev1.data.model.blog.Tag;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogPageVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.TagVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController extends BaseController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/blog")
    public String showBlogPage(Model model,
                               @Valid @ModelAttribute("search") ProductSearchVM search,
                               @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                               @RequestParam(name = "size", required = false, defaultValue = "4") Integer size,
                               @RequestParam(name = "tag", required = false, defaultValue = "0") Integer tagId,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);
        BlogPageVM vm = new BlogPageVM();

        PageRequest pageRequest = new PageRequest(page, size);
        Page<Blog> blogPage;
        if (search != null) {
                blogPage = blogService.getActiveBlogPageFromSearchAndTag(pageRequest, search.getName(), tagId);
        } else {
            blogPage = blogService.getActiveBlogPageFromSearchAndTag(pageRequest, null, tagId);
        }

        List<BlogVM> blogVMList = new ArrayList<>();

        for (Blog blog : blogPage.getContent()) {
            BlogVM blogVM = new BlogVM();
            blogVM.setId(blog.getId());
            blogVM.setTitle(blog.getTitle());
            blogVM.setShortImg(blog.getShortImg());
            blogVM.setShortDesc(blog.getShortDesc());
            blogVM.setCreatedDate(blog.getCreatedDate());
            blogVMList.add(blogVM);
        }

        List<TagVM> tagVMList = new ArrayList<>();
        List<Tag> tagList = tagService.getAll();

        for (Tag tag : tagList) {
            TagVM tagVM = new TagVM();
            tagVM.setId(tag.getId());
            tagVM.setName(tag.getName());
            tagVM.setBlogCount(blogService.countByTag(tag.getId()));
            tagVMList.add(tagVM);
        }

        vm.setBlogVMList(blogVMList);
        vm.setTagVMList(tagVMList);

        HeaderMenuVM headerMenuVM = this.getHeaderMenuVM(cartQty, principal);
        headerMenuVM.setPageName("blog");
        vm.setHeaderMenuVM(headerMenuVM);

        model.addAttribute("vm", vm);
        model.addAttribute("page", blogPage);
        return "blog";
    }


}
