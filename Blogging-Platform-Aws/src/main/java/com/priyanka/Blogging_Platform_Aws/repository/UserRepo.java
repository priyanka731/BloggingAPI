package com.priyanka.Blogging_Platform_Aws.repository;

import com.priyanka.Blogging_Platform_Aws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findFirstByUserEmail(String existingEmail);
}