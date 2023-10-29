package com.priyanka.Blogging_Platform_Aws.repository;

import com.priyanka.Blogging_Platform_Aws.model.Comment;
import com.priyanka.Blogging_Platform_Aws.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostOwner(Post myPost);

    Comment findFirstByPostOwner(Post post);
}
