package com.xbaychallenge.user.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

    private String email;
    private String firstName;
    private String lastName;

}
