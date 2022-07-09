package com.cimspace.stockup_user.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserDTO {

    private String id;

    @NotNull
    @Size(max = 50)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @Size(max = 255)
    private String telephone;

    @NotNull
    private String email;

}
