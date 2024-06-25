package com.interactive.hana.domain.consulting.domain;

import com.interactive.hana.domain.user.domain.User;
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
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsultingStateType state;

    @OneToOne(fetch = FetchType.LAZY)
    private ConsultingAnswer consultingAnswer;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @Builder
    public Consulting(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.state = ConsultingStateType.WAIT;
    }

    public void answer(ConsultingAnswer consultingAnswer) {
        this.state = ConsultingStateType.COMPLETE;
        this.consultingAnswer = consultingAnswer;
    }

}
