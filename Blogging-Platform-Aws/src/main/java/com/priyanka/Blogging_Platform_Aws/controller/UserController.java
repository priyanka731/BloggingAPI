package com.priyanka.Blogging_Platform_Aws.controller;

import com.priyanka.Blogging_Platform_Aws.model.Post;
import com.priyanka.Blogging_Platform_Aws.model.User;
import com.priyanka.Blogging_Platform_Aws.model.dto.SignInDto;
import com.priyanka.Blogging_Platform_Aws.service.Password;
import com.priyanka.Blogging_Platform_Aws.service.PostService;
import com.priyanka.Blogging_Platform_Aws.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @PostMapping("user/signUp")
    public String userSignUp(@Valid @RequestBody User user){
        return userService.userSignUp(user);
    }

    @PostMapping("User/signIn")
    public String userSignIn(SignInDto signInDto){
        return userService.userSignIn(signInDto);
    }

    @GetMapping("postById/{postId}")
    public Post getPostById(@RequestParam String email, @RequestParam String token, @PathVariable Integer postId)
    {
        return userService.getPostById(email, token, postId);
    }
    @PutMapping("change/password")
    public String changePassword(@RequestParam String email, @RequestParam String token, @RequestBody Password password){
        return userService.changePassword(email, token, password);
    }
    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }
}
