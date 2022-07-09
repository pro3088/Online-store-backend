package com.cimspace.stockup_user.user_address;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserAddressDTO {

    private String id;

    @NotNull
    private String addressline1;

    private String addressline2;

    private Integer telephone;

    @NotNull
    private String userId;

}
