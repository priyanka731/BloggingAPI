package com.priyanka.Blogging_Platform_Aws.controller;

import com.priyanka.Blogging_Platform_Aws.model.Follower;
import com.priyanka.Blogging_Platform_Aws.service.FollowerService;
import com.priyanka.Blogging_Platform_Aws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowerController {

    @Autowired
    UserService userService;

    @Autowired
    FollowerService followerService;

    @PostMapping("follow/user/{targetUserId}")
    public String followTarget(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer targetUserId)
    {
        return followerService.followTarget(email,tokenValue,targetUserId);
    }

    @GetMapping("all/follower")
    public List<Follower> getAllFollower(@RequestParam String email, @RequestParam String tokenValue){
        return followerService.getAllFollower(email,tokenValue);
    }
}
