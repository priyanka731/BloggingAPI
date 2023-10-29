package com.priyanka.Blogging_Platform_Aws.repository;

import com.priyanka.Blogging_Platform_Aws.model.Follower;
import com.priyanka.Blogging_Platform_Aws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepo extends JpaRepository<Follower,Integer> {

    List<Follower> findByUserAndFollowers(User follower, User target);


    List<Follower> findByUser(User follow);
}
