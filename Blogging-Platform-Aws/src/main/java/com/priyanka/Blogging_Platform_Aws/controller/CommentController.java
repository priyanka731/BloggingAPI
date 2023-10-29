package com.priyanka.Blogging_Platform_Aws.controller;

import com.priyanka.Blogging_Platform_Aws.model.Comment;
import com.priyanka.Blogging_Platform_Aws.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("comment/post/{postId}")
    public String addComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId, @RequestBody String commentBody )
    {
        return commentService.addComment(email,tokenValue,commentBody,postId);
    }



    @GetMapping("all/comment/{postId}")
    public List<Comment> getCommentByPostId(@RequestParam String email, @RequestParam String tokenValue,
                                            @PathVariable Integer postId){
        return commentService.getCommentByPostId(email,tokenValue,postId);
    }

    @PutMapping("update/comment/{CommentId}")
    public String updateCommentByPostId(@RequestParam String email, @RequestParam String tokenValue,
                                        @PathVariable Integer CommentId, @RequestBody Comment newComment){
        return commentService.updateCommentByPostId(email,tokenValue,CommentId, newComment);
    }

    @DeleteMapping("post/comment/{commentId}")
    public String removeComment(@RequestParam String email, @RequestParam String tokenValue,
                                @PathVariable Integer commentId)
    {
        return commentService.removeComment(email,tokenValue,commentId);
    }
}
