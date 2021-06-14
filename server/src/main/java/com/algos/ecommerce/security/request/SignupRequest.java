package com.algos.ecommerce.security.request;

import com.algos.ecommerce.user.model.Role;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> role;


}
