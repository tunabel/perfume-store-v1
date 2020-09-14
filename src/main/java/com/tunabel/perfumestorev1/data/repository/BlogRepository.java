package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @Query("SELECT b FROM Blog b " +
            "WHERE (:search IS NULL OR UPPER(b.title) LIKE CONCAT('%',UPPER(:search),'%'))")
    Page<Blog> getPageContaining(Pageable pageable, @Param("search") String search);

    @Query(value = "SELECT COUNT(b.blog_id) FROM dbo_blog b " +
            "JOIN dbo_blog_tag bt WHERE bt.tag_id = :tagId", nativeQuery = true)
    int findAllByTagId(@Param("tagId") int tagId);
}
