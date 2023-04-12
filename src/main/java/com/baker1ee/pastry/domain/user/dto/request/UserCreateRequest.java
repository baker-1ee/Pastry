package com.baker1ee.pastry.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    private Long userSeq;

    @NotBlank
    private String name;

}
