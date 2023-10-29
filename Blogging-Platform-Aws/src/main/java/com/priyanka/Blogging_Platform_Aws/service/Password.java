package com.priyanka.Blogging_Platform_Aws.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Password {

    private String userEmail;
    private String userPassword;
}

