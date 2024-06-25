package com.interactive.hana.domain.user.domain;

import com.interactive.hana.domain.consulting.domain.Consulting;
import com.interactive.hana.domain.contract.domain.Contract;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String address;

    private String residentRegistrationNumber;

    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    @Enumerated(EnumType.STRING)
    private UserStateType state;

    @OneToMany(mappedBy = "user")
    private List<Contract<?>> contractList;

    @OneToMany(mappedBy = "user")
    private List<Consulting> consultingList;

//    @OneToMany(mappedBy = "user")
//    private List<ConsultingAnswer> consultingAnswerList;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @Builder
    public User(String email, String name, String password, String phoneNumber, String address,
                String residentRegistrationNumber, UserRoleType role, UserStateType state) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.role = role;
        this.state = state;
        this.contractList = new ArrayList<>();
        this.consultingList = new ArrayList<>();
    }

    public void emailVerificationCompleted() {
        this.state = UserStateType.NORMAL;
    }

}
