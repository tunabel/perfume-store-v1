package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
