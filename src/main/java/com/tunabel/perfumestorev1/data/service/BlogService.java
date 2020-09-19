package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import com.tunabel.perfumestorev1.data.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
