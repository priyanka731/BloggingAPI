package com.priyanka.Blogging_Platform_Aws.model;


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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotBlank(message = "Comment body is required")
    private String commentBody;

    @NotNull(message = "Comment creation timestamp is required")
    private LocalDateTime commentCreationTimeStamp;

    @NotNull(message = "Commenter is required")
    @ManyToOne
    private User commenter;

    @NotNull(message = "Post owner is required")
    @ManyToOne
    private Post postOwner;
}

