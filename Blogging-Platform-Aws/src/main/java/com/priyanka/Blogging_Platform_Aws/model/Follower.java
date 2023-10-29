package com.priyanka.Blogging_Platform_Aws.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Follower {

    @Id
    private Integer followerId;

    @ManyToOne
    private User user;

    @OneToOne
    private User followers;
}
