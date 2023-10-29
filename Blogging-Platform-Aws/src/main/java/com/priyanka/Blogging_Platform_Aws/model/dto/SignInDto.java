package com.priyanka.Blogging_Platform_Aws.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignInDto {

    private String userEmail;
    private String userPassword;
}
