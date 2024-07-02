package com.interactive.hana.domain.contract.dto;

import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.user.domain.User;

public abstract class ContractCreateRequest<T, U extends Insurance> {

    public abstract Long getInsuranceId();

    public abstract T toEntity(User user, U u, Long payment);

}
