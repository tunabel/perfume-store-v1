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
            "WHERE b.blog_id IN (" +
            "SELECT bt.blog_id FROM dbo_blog_tag bt WHERE bt.tag_id = :tagId)", nativeQuery = true)
    int findAllByTagId(@Param("tagId") int tagId);

//      Pageable doesn't work with native query.
//    @Query(value = "SELECT b FROM dbo_blog b " +
//            "WHERE (" +
//            ":search IS NULL " +
//            "OR UPPER(b.title) LIKE CONCAT('%',UPPER(:search),'%'))" +
//            "AND b.status = 1 " +
//            "AND b.blog_id IN (" +
//            "SELECT bt.blog_id FROM dbo_blog_tag bt WHERE bt.tag_id = :tagId)", nativeQuery = true)
//    Page<Blog> getActiveBlogPageFromSearchAndTag(Pageable pageRequest, @Param("search") String search, @Param("tagId") int tagId);

    @Query(value = "SELECT b FROM Blog b " +
            "JOIN b.tagList t " +
            "WHERE (:search IS NULL OR UPPER(b.title) LIKE CONCAT('%',UPPER(:search),'%')) " +
            "AND b.status = 1 " +
            "AND t.id = :tagId")
    Page<Blog> getActiveBlogPageFromSearchAndTag(Pageable pageRequest, @Param("search") String search, @Param("tagId") int tagId);

    @Query("SELECT b FROM Blog b " +
            "WHERE (:search IS NULL OR UPPER(b.title) LIKE CONCAT('%',UPPER(:search),'%')) AND b.status = 1")
    Page<Blog> getActiveBlogPageFromSearch(Pageable pageRequest, @Param("search") String search);
}
