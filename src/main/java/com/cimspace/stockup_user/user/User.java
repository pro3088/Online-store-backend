package com.cimspace.stockup_user.user;

import com.cimspace.stockup_user.user_address.UserAddress;
import com.cimspace.stockup_user.user_payment.UserPayment;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, columnDefinition = "text")
    private String password;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column
    private String telephone;

    @Column(nullable = false, unique = true, columnDefinition = "text")
    private String email;

    @OneToOne(
            mappedBy = "userId",
            fetch = FetchType.LAZY,
            optional = false
    )
    private UserAddress userId;

    @OneToMany(mappedBy = "userId")
    private Set<UserPayment> userIdUserPayments;


}
