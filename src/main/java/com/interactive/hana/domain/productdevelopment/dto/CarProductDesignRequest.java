package com.interactive.hana.domain.productdevelopment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarProductDesignRequest implements DesignRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long carPrice;

    @NotNull
    private LocalDate carReleaseDate;

    @NotNull
    private Long drivingDistance;

}
