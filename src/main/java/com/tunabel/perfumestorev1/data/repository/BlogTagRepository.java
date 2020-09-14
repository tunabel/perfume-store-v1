package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.blog.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogTagRepository extends JpaRepository<BlogTag, Integer> {
}
