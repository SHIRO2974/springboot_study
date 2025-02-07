package com.korit.springboot_study.entity.study;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int userId;
    private String username;
    private String name;

    @JsonIgnore
    private String password;
    private String email;
}
