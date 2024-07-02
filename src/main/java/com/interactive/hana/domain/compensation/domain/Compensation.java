package com.interactive.hana.domain.compensation.domain;

import com.interactive.hana.domain.accident.domain.Accident;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long compensationAmount;

    @ManyToOne
    private Accident accident;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    private Compensation(Long compensationAmount, Accident accident) {
        this.compensationAmount = compensationAmount;
        this.accident = accident;
    }

    public static Compensation of(Long compensationAmount, Accident accident) {
        return new Compensation(compensationAmount, accident);
    }

}
