package com.interactive.hana.domain.accident.domain;

import com.interactive.hana.domain.compensation.domain.Compensation;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.global.file.file.MyFile;
import lombok.AccessLevel;
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
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<MyFile> files;

    @ManyToOne
    private Contract<?> contract;

    @OneToMany(mappedBy = "accident")
    private List<Compensation> compensationList;

    @Enumerated(EnumType.STRING)
    private AccidentState state;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    public Accident(Contract<?> contract) {
        this.files = new ArrayList<>();
        this.contract = contract;
        this.compensationList = new ArrayList<>();
        this.state = AccidentState.WAIT;
    }

    public void addFile(MyFile file) {
        this.files.add(file);
    }

    public void compensationApprove(Compensation compensation) {
        this.state = AccidentState.APPROVE;
        this.compensationList.add(compensation);
    }

    public void compensationReject() {
        this.state = AccidentState.REJECT;
    }

}
