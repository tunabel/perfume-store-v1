package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import com.tunabel.perfumestorev1.data.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public Page<Blog> getBlogPageContaining(Pageable pageable, String search) {
        return blogRepository.getPageContaining(pageable, search);
    }

    public int countByTag(int tagId) {
        return blogRepository.findAllByTagId(tagId);
    }

    public Blog getById(int blogId) {
        return blogRepository.findOne(blogId);
    }

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    public boolean switchStatus(int blogId) {
        Blog blog = blogRepository.getOne(blogId);

        if (blog != null) {
            int currStatus = blog.getStatus();
            if (currStatus == 0) {
                blog.setStatus(1);
            } else {
                blog.setStatus(0);
            }

            blogRepository.save(blog);
            return true;
        } else {
            return false;
        }
    }

    public Page<Blog> getActiveBlogPageFromSearchAndTag(Pageable pageRequest, String search, int tagId) {
        if (tagId == 0) {
            return blogRepository.getActiveBlogPageFromSearch(pageRequest, search);
        } else {
            return blogRepository.getActiveBlogPageFromSearchAndTag(pageRequest, search, tagId);
        }
    }

    public List<Blog> getRecentList(int limit) {
        return blogRepository.getRecentBlogList(limit);
    }
}
