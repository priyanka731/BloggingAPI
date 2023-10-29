package com.priyanka.Blogging_Platform_Aws.service;

import com.priyanka.Blogging_Platform_Aws.model.Comment;
import com.priyanka.Blogging_Platform_Aws.model.Post;
import com.priyanka.Blogging_Platform_Aws.repository.CommentRepo;
import com.priyanka.Blogging_Platform_Aws.repository.PostRepo;

import com.priyanka.Blogging_Platform_Aws.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    CommentRepo commentRepo;
    public void createPost(Post post) {
        postRepo.save(post);
    }

    public Post findPost(Integer postId) {
        return postRepo.findById(postId).orElseThrow();

    }
    public String deletePost(String email, String tokenValue, Integer postId) {
        if(authenticationService.authenticate(email, tokenValue)){
            Post post = postRepo.findById(postId).orElseThrow();
            String postUserEmail = post.getPostUser().getUserEmail();

            Comment comment = commentRepo.findFirstByPostOwner(post);
            if(comment != null){
                commentRepo.delete(comment);
            }

            if(email.equals(postUserEmail)){
                postRepo.delete(post);
                return "post deleted";
            }else{
                return "Invalid User";
            }

        }else {
            return "Un Authenticated access!!!";
        }
    }
    public void delete(Post post) {
        postRepo.delete(post);
    }

}



