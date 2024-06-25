package com.interactive.hana.domain.consulting.domain;

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
public class ConsultingAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @OneToOne(mappedBy = "consultingAnswer", fetch = FetchType.LAZY)
    private Consulting consulting;

//    @ManyToOne
//    private User user;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @Builder
    public ConsultingAnswer(String title, String contents, Consulting consulting) {
        this.title = title;
        this.contents = contents;
        this.consulting = consulting;
    }

}
