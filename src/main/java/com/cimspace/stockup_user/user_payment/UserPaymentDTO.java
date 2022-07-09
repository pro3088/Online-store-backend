package com.cimspace.stockup_user.user_payment;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserPaymentDTO {

    private String id;

    private PaymentTypes paymentType;

    private Integer accountno;

    @NotNull
    private String userId;

}
