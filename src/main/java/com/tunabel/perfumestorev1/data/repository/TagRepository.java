package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.blog.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "SELECT * FROM dbo_tag t " +
            "WHERE t.tag_id NOT IN ( " +
            "SELECT bt.tag_id FROM dbo_blog_tag bt " +
            "WHERE bt.blog_id = :blogId) " +
            "ORDER BY t.tag_name", nativeQuery = true)
    List<Tag> findAllExceptForBlogId(@Param("blogId") int blogId);

    @Query(value = "SELECT t FROM Tag t " +
            "WHERE UPPER(t.name) LIKE CONCAT('%',UPPER(:tagName),'%') ")
    List<Tag> findByName(@Param("tagName") String tagName);
}
