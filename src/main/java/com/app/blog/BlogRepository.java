package com.app.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BlogRepository extends JpaRepository<Blog, Long> {
}
