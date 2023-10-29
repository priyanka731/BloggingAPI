package com.priyanka.Blogging_Platform_Aws.service;

import com.priyanka.Blogging_Platform_Aws.model.AuthenticationToken;

import com.priyanka.Blogging_Platform_Aws.model.Comment;
import com.priyanka.Blogging_Platform_Aws.model.Post;
import com.priyanka.Blogging_Platform_Aws.model.User;
import com.priyanka.Blogging_Platform_Aws.repository.AuthenticationRepo;
import com.priyanka.Blogging_Platform_Aws.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepo authenticationRepo;

    @Autowired
    UserRepo userRepo;
    public void createToken(AuthenticationToken token) {
        authenticationRepo.save(token);
    }

    public boolean authenticate(String email, String token) {

        AuthenticationToken tokenValue = authenticationRepo.findFirstByTokenValue(token);
        if(tokenValue != null){
            return tokenValue.getUser().getUserEmail().equals(email);
        }else{
            return false;
        }
    }

    public void deleteToken(String token) {
        AuthenticationToken token1 = authenticationRepo.findFirstByTokenValue(token);
        authenticationRepo.delete(token1);
    }

    public boolean authorizedCommentRemover(String email, Post post, Comment commentToBeDeleted) {
        User authUser = userRepo.findFirstByUserEmail(email);

        return authUser.equals(post.getPostUser()) || authUser.equals(commentToBeDeleted.getCommenter());
    }
}
