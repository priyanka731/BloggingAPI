package com.priyanka.Blogging_Platform_Aws.service;

import com.priyanka.Blogging_Platform_Aws.model.Follower;
import com.priyanka.Blogging_Platform_Aws.model.User;
import com.priyanka.Blogging_Platform_Aws.repository.FollowerRepo;
import com.priyanka.Blogging_Platform_Aws.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    @Autowired
    FollowerRepo followerRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepo userRepo;


    public String followTarget(String email, String tokenValue, Integer targetUserId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            User follower = userRepo.findFirstByUserEmail(email);
            User target = userRepo.findById(targetUserId).orElseThrow();
            if (authorizeToFollow(follower, target)) {
                Follower follower1 = new Follower(null, follower, target);
                followerRepo.save(follower1);
                return follower.getUserHandle() + " started following " + target.getUserHandle();
            } else {
                return "Already follows, cannot re-follow";
            }
        } else {
            return "Un Authenticated access!!!";
        }
    }


    private boolean authorizeToFollow(User follower, User target) {
        boolean followingExist = findByTargetAndFollower(follower,target);

        return !followingExist && !follower.equals(target);
    }
    public boolean findByTargetAndFollower(User follower, User target) {
        List<Follower> followers = followerRepo.findByUserAndFollowers(follower,target);

        return !followers.isEmpty();
    }

    public List<Follower> getAllFollower(String email, String tokenValue) {
        if (authenticationService.authenticate(email, tokenValue))
        {
            User follow = userRepo.findFirstByUserEmail(email);
            return followerRepo.findByUser(follow);
        }
        return null;
    }
}

