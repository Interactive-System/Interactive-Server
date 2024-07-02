package com.interactive.hana.domain.capacitypolicy.domain;

import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyUpdateRequest;
import com.interactive.hana.domain.insurance.domain.Insurance;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CapacityPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "capacityPolicy", fetch = FetchType.LAZY)
    private Insurance insurance;

    private String physical;

    private String economical;

    private String environmental;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @Builder
    public CapacityPolicy(String name, Insurance insurance, String physical, String economical, String environmental) {
        this.name = name;
        this.insurance = insurance;
        this.physical = physical;
        this.economical = economical;
        this.environmental = environmental;
    }

    public void update(CapacityPolicyUpdateRequest dto) {
        this.name = dto.getName();
        this.physical = dto.getPhysical();
        this.economical = dto.getEconomical();
        this.environmental = dto.getEnvironmental();
    }

    public CapacityPolicy removeInsurance() {
        getInsurance().removeCapacityPolicy();
        this.insurance = null;
        return this;
    }
}
