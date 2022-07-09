package com.cimspace.stockup_user.user_address;

import com.cimspace.stockup_user.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
public class UserAddress {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, columnDefinition = "text")
    private String addressline1;

    @Column(columnDefinition = "text")
    private String addressline2;

    @Column
    private Integer telephone;

    @OneToOne
    @JoinColumn(name = "user_id_id", nullable = false)
    private User userId;

}
