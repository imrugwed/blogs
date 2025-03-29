package com.app.blog;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BlogService {
    Blog createBlog(Blog blog);
    Page<Blog> getAllBlogs(int page, int size);
    Blog getBlogById(Long id);
    Blog updateBlog(Long id, Blog updatedBlog);
    void deleteBlog(Long id);
}
