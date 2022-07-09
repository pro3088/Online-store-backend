package com.cimspace.stockup_user.user_payment;

import com.cimspace.stockup_user.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
public class UserPayment {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentTypes paymentType;

    @Column
    private Integer accountno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_id", nullable = false)
    private User userId;

}
