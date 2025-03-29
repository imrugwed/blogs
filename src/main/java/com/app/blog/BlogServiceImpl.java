package com.app.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> getAllBlogs(int page, int size) {
        return blogRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(()->new NotFoundException(id,"Blog"));
    }

    @Override
    public Blog updateBlog(Long id, Blog updatedBlog) {

        return blogRepository.findById(id)
                .map(blog -> {
                    blog.setTitle(updatedBlog.getTitle());
                    blog.setContent(updatedBlog.getContent());
                    blog.setAuthor(updatedBlog.getAuthor());
                    return blogRepository.save(blog);
                }).orElseThrow(()->new NotFoundException(id,"Blog"));
    }

    @Override
    public void deleteBlog(Long id) {
        getBlogById(id);
        blogRepository.deleteById(id);
    }
}