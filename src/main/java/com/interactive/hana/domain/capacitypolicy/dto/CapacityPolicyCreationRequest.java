package com.interactive.hana.domain.capacitypolicy.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.capacitypolicy.domain.CapacityPolicy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CapacityPolicyCreationRequest {

    @NotNull
    private Long insuranceId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String physical;

    @NotEmpty
    private String economical;

    @NotEmpty
    private String environmental;

    public CapacityPolicy toEntity() {
        return CapacityPolicy.builder()
                .name(this.name)
                .physical(this.physical)
                .economical(this.economical)
                .environmental(this.environmental)
                .build();
    }

}
