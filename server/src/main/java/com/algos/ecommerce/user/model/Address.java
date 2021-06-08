package com.algos.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressDetailsID;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    private String town;

    private String city;

    private String postcode;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userDetails_id")
    private UserDetails userDetails;

}
