package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.blog.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
