package com.priyanka.Blogging_Platform_Aws.service;

import com.priyanka.Blogging_Platform_Aws.model.AuthenticationToken;
import com.priyanka.Blogging_Platform_Aws.model.Post;
import com.priyanka.Blogging_Platform_Aws.model.User;
import com.priyanka.Blogging_Platform_Aws.model.dto.SignInDto;
import com.priyanka.Blogging_Platform_Aws.repository.PostRepo;
import com.priyanka.Blogging_Platform_Aws.repository.UserRepo;
import com.priyanka.Blogging_Platform_Aws.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {


    @Autowired
    PostService postService;

    @Autowired
    PostRepo postRepo;

    @Autowired
    FollowerService followService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserRepo userRepo;


    public String userSignUp(User user) {
        String userEmail = user.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(userEmail);
        if (existingUser != null) {
            return "Email is Already Registered !!!";
        }

        String password = user.getUserPassword();
        try {
            String userPassword = PasswordEncryptor.encrypt(password);
            user.setUserPassword(userPassword);
            userRepo.save(user);
            return "User Registered!!!";
        } catch (Exception e) {
            return "Invalid Credential!!!";
        }
    }

    public String userSignIn(SignInDto signInDto) {
        String userEmail = signInDto.getUserEmail();
        String userPassword = signInDto.getUserPassword();
        User existingUser = userRepo.findFirstByUserEmail(userEmail);
        if(existingUser == null){
            return "User Doesn't exist, SignUp first !!!";
        }

        try{
            String password = PasswordEncryptor.encrypt(userPassword);
            if(existingUser.getUserPassword().equals(password)){
                AuthenticationToken token = new AuthenticationToken(existingUser);
                authenticationService.createToken(token);
                return token.getTokenValue();
            }else{
                return "Invalid Credential!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Unauthorised user!!!";
        }
    }

    public String userSignOut(String email, String token) {

        if(authenticationService.authenticate(email,token)) {
            authenticationService.deleteToken(token);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String createPost(String email, String tokenValue, Post post) {
        if(authenticationService.authenticate(email,tokenValue)) {
            User existingUser = userRepo.findFirstByUserEmail(email);
            post.setPostUser(existingUser);
            postService.createPost(post);
            return "Post has been Uploaded";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public List<Post> getAllPost() {

        List<Post> posts = postRepo.findAll();
        for(Post post : posts){
            post.setPostUser(null);
        }
        return posts;
    }

    public Post getPostById(String email, String token, Integer postId) {

        if(authenticationService.authenticate(email,token)) {
            Post post = postRepo.findById(postId).orElseThrow();
            post.setPostUser(null);
            return post;
        }
        else
            return null;
    }

    public String updatePost(String email, String tokenValue, Integer postId, Post post) {

        if(authenticationService.authenticate(email, tokenValue)){
            Post existingPost = postRepo.findById(postId).orElseThrow();
            User existingUser = userRepo.findFirstByUserEmail(email);
            if(existingPost.getPostUser().equals(existingUser)) {
                existingPost.setPostCaption(post.getPostCaption());
                existingPost.setPostCreatedTimeStamp(post.getPostCreatedTimeStamp());
                postRepo.save(post);
                return "updated post";
            }else{
                return "Post Not found";
            }
        }else {
            return "Un Authenticated access!!!";
        }
    }

    public String changePassword(String email, String tokenValue,Password password) {
        if(authenticationService.authenticate(email,tokenValue)){
            User existingUser = userRepo.findFirstByUserEmail(email);
            String userEmail = password.getUserEmail();
            String newPassword = password.getUserPassword();
            if(existingUser.getUserEmail().equals(userEmail)){
                existingUser.setUserPassword(newPassword);
                return "your password has been changed";
            }
            return  userEmail + " is invalid";
        }else{
            return "Un Authenticated access!!!";
        }
    }

}
