package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import com.tunabel.perfumestorev1.data.model.blog.Tag;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogDetailVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogPageVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.TagVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.*;
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
@RequestMapping(path = "/blog")
public class BlogController extends BaseController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "")
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

    @GetMapping(value = "/post/{blogId}")
    public String showBlogPost(Model model,
                               @Valid @ModelAttribute("search") ProductSearchVM search,
                               @PathVariable("blogId") int blogId,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);
        BlogDetailVM vm = new BlogDetailVM();

        Blog blog = blogService.getById(blogId);


        if (blog != null) {

            vm.setId(blog.getId());
            vm.setTitle(blog.getTitle());
            vm.setShortImg(blog.getShortImg());
            vm.setFullImg(blog.getFullImg());
            vm.setShortDesc(blog.getShortDesc());
            vm.setFullDesc(blog.getFullDesc());
            vm.setCreatedDate(blog.getCreatedDate());
            //set tag of blog post
            List<TagVM> tagVMS = new ArrayList<>();

            for (Tag tag : blog.getTagList()) {
                TagVM tagVM = new TagVM();
                tagVM.setId(tag.getId());
                tagVM.setName(tag.getName());

                tagVMS.add(tagVM);
            }

            Collections.sort(tagVMS, (tag1, tag2) ->
                    tag1.getName().compareToIgnoreCase(tag2.getName())
            );

            vm.setBlogtagVMList(tagVMS);
        }

        //set tag search list
        List<TagVM> tagVMList = new ArrayList<>();
        List<Tag> tagList = tagService.getAll();

        for (Tag tag : tagList) {
            TagVM tagVM = new TagVM();
            tagVM.setId(tag.getId());
            tagVM.setName(tag.getName());
            tagVM.setBlogCount(blogService.countByTag(tag.getId()));
            tagVMList.add(tagVM);
        }
        vm.setAlltagVMList(tagVMList);

        //set RecentBlog list
        List<Blog> recentBlogList = blogService.getRecentList(5);
        List<BlogVM> recentBlogVMList = new ArrayList<>();

        for (Blog recentBlog : recentBlogList) {
            BlogVM blogVM = new BlogVM();
            blogVM.setId(recentBlog.getId());
            blogVM.setTitle(recentBlog.getTitle());
            blogVM.setShortImg(recentBlog.getShortImg());
            recentBlogVMList.add(blogVM);
        }

        vm.setRecentBlogVMList(recentBlogVMList);

        HeaderMenuVM headerMenuVM = this.getHeaderMenuVM(cartQty, principal);
        headerMenuVM.setPageName("blog");
        vm.setHeaderMenuVM(headerMenuVM);

        model.addAttribute("vm", vm);
        return "blog-detail";
    }


}
