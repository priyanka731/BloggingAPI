package com.priyanka.Blogging_Platform_Aws.controller;

import com.priyanka.Blogging_Platform_Aws.model.Post;
import com.priyanka.Blogging_Platform_Aws.service.PostService;
import com.priyanka.Blogging_Platform_Aws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("Post/vlog")
    public String createPost(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Post post)
    {
        return userService.createPost(email,tokenValue,post);
    }

    @GetMapping("All/Post")
    public List<Post> getAllPost(){
        return userService.getAllPost();
    }

    @PutMapping("update/Post/{postId}")
    public String updatePost(@RequestParam String email, @RequestParam String tokenValue,
                             @PathVariable Integer postId, @RequestBody Post post)
    {
        return userService.updatePost(email,tokenValue,postId,post);
    }

    @DeleteMapping("Post/{postId}")
    public String deletePost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return postService.deletePost(email,tokenValue,postId);
    }


}
