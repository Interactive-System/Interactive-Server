package com.interactive.hana.domain.productdevelopment.domain;

import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class ProductDevelopment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dtype", insertable = false, updatable = false)
    private String dtype;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long payment;

    private int expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DevelopmentState state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApproveState approveState;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    public ProductDevelopment(String name, Long payment, int expirationDate) {
        this.name = name;
        this.payment = payment;
        this.expirationDate = expirationDate;
    }

    protected void changeState(DevelopmentState state) {
        this.state = state;
    }

    protected void changeApproveSate(ApproveState approveState) {
        this.approveState = approveState;
    }

    public abstract ProductPlanDevelopmentResponse toResponse();

}
