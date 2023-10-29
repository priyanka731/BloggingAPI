package com.priyanka.Blogging_Platform_Aws.model;

import com.priyanka.Blogging_Platform_Aws.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;

    @NotBlank(message = "Email is required")
    @Column(unique = true)  //new thing  -
    @Email
    private String userEmail;

    @NotBlank
    private String userPassword; //regex for strong password

    @NotBlank(message = "User handle is required")
    private String userHandle;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}



