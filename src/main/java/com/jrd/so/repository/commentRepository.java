package com.jrd.so.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrd.so.models.Comment;

@Repository
public interface commentRepository extends JpaRepository<Comment, Long> {

}
