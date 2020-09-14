package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;
}
