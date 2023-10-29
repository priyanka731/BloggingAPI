package com.priyanka.Blogging_Platform_Aws.model.dto;

import com.priyanka.Blogging_Platform_Aws.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userEmail;
    private String tokenValue;

    private Comment comment;
}

