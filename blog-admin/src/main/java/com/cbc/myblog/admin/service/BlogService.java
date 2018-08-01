package com.cbc.myblog.admin.service;


import com.cbc.myblog.admin.domain.Blog;
import com.cbc.myblog.admin.domain.Catalog;
import com.cbc.myblog.admin.domain.User;
import com.cbc.myblog.admin.repository.BlogRepository;
import com.cbc.myblog.admin.repository.CatalogRepository;
import com.cbc.myblog.admin.util.CurrentUserUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    private final CatalogRepository catalogRepository;

    public Blog addBlog(Blog blog) {
        User user = CurrentUserUtil.getInstance().getUser();
        blog.setCreateDate(LocalDateTime.now());
        blog.setUpdateDate(LocalDateTime.now());
        blog.setUserId(user.getId());
        blog.setCommentSize(0L);
        blog.setReadSize(0L);
        blogRepository.save(blog);
        return blog;
    }

    public Blog getBlogById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        blog.ifPresent(u -> {
            Optional<Catalog> catalog = catalogRepository.findById(u.getCatalogId());
            u.setCatalog(catalog.orElseGet(Catalog::new));
        });
        return blog.orElseGet(Blog::new);
    }

    public void updateBlog(Blog blog) {
        blog.setUpdateDate(LocalDateTime.now());
        blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Page<Blog> getBlog(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        blogs.getContent().forEach(u -> {
            Optional<Catalog> catalog = catalogRepository.findById(u.getCatalogId());
            u.setCatalog(catalog.orElseGet(Catalog::new));
        });
        return blogs;
    }

    public List<String> getTags() {
        return blogRepository.findTags();
    }

    public List<Blog> getBlogsByYear(String year) {
        List<Blog> blogs = blogRepository.getBlogsByYear(year);

        return blogs;
    }
}
