package com.interactive.hana.domain.contract.domain;

import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Contract<Res> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dtype", insertable = false, updatable = false)
    private String dtype;

    @ManyToOne
    private User user;

    @OneToOne
    private Insurance insurance;

    private String customerPhysical;

    private String customerEconomical;

    private String customerEnvironmental;

    private Long calculatedPayment;

    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private UwDueProcessType uwDueProcessType;

    @OneToMany(mappedBy = "contract")
    private List<Accident> accidentList;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    public Contract(User user, Insurance insurance, String customerPhysical, String customerEconomical,
                    String customerEnvironmental, Long calculatedPayment) {
        this.user = user;
        this.insurance = insurance;
        this.customerPhysical = customerPhysical;
        this.customerEconomical = customerEconomical;
        this.customerEnvironmental = customerEnvironmental;
        this.calculatedPayment = calculatedPayment;
        this.uwDueProcessType = UwDueProcessType.WAIT;
        this.accidentList = new ArrayList<>();
    }

    public Contract<Res> uwDueProcessApprove() {
        if (this.uwDueProcessType.equals(UwDueProcessType.APPROVE))
            throw new IllegalArgumentException("해당 계약은 이미 수락되었습니다.");
        this.uwDueProcessType = UwDueProcessType.APPROVE;
        this.expirationDate = LocalDate.now().plusDays(insurance.getExpirationDate());
        return this;
    }

    public Contract<Res> uwDueProcessReject() {
        if (this.uwDueProcessType.equals(UwDueProcessType.REJECT))
            throw new IllegalArgumentException("해당 계약은 이미 거절되었습니다.");
        this.uwDueProcessType = UwDueProcessType.REJECT;
        this.expirationDate = null;
        return this;
    }

    public abstract Res toResponse();

}
