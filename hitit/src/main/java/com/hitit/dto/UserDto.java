package com.hitit.dto;

import com.hitit.entity.RepositoryTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private RepositoryTable repository_id;
    private String username;
    private String location;
    private String company;
    private int contributions;
}
