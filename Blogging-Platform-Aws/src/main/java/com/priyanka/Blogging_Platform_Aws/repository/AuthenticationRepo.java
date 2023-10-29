package com.priyanka.Blogging_Platform_Aws.repository;

import com.priyanka.Blogging_Platform_Aws.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepo extends JpaRepository<AuthenticationToken, Integer> {


    AuthenticationToken findFirstByTokenValue(String token);
}
