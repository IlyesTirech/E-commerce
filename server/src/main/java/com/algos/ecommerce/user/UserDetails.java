package com.algos.ecommerce.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailsID;

    @NotBlank @Size(max = 50)
    private String name;

    @NotBlank @Size(max = 50)
    private String surname;

    @NotBlank
    private Date dateOfBirth;

    private Integer mobileNumber;

    private Integer homeNumber;

    @NotBlank @OneToMany
    private List<Address> addresses = new ArrayList<>();


}
