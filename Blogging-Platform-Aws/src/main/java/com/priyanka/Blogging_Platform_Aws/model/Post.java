package com.priyanka.Blogging_Platform_Aws.model;


import com.priyanka.Blogging_Platform_Aws.model.enums.PostType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer postId;

        @NotBlank(message = "Post content is required")
        private String postContent;

        @NotBlank(message = "Post caption is required")
        private String postCaption;

        private String postLocation;

        @NotNull(message = "Post type is required")
        private PostType postType;

        private String postLink;

        @NotNull(message = "Post created timestamp is required")
        private LocalDateTime postCreatedTimeStamp;

        @NotNull(message = "Post user is required")
        @ManyToOne
        private User postUser;
    }