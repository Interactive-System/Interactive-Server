package com.interactive.hana.domain.insurance.domain;

import com.interactive.hana.domain.capacitypolicy.domain.CapacityPolicy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dtype", insertable = false, updatable = false)
    private String dtype;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long payment;

    @Column(nullable = false)
    private int expirationDate;

    @OneToOne
    private CapacityPolicy capacityPolicy;

    private boolean availableSale;

    public Insurance(String name, Long payment, int expirationDate) {
        this.name = name;
        this.payment = payment;
        this.expirationDate = expirationDate;
        this.availableSale = false;
    }

    public void setCapacityPolicy(CapacityPolicy capacityPolicy) {
        this.capacityPolicy = capacityPolicy;
        this.availableSale = true;
    }

    public boolean hasCapacityPolicy() {
        return this.capacityPolicy != null;
    }

    public void removeCapacityPolicy() {
        this.capacityPolicy = null;
        this.availableSale = false;
    }

//    public abstract DetailRes toDetailResponse();
    public abstract <T> T toDetailResponse();

}
