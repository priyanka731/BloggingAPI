package com.priyanka.Blogging_Platform_Aws.repository;

import com.priyanka.Blogging_Platform_Aws.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;




public interface PostRepo extends JpaRepository<Post,Integer> {

}
