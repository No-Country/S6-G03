package com.nocountry.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotEmpty(message = "{firstname.notnull}")
    protected String firstName;

    @NotEmpty(message = "{lastname.notnull}")
    protected String lastName;

    @Email(message = "{email.pattern}")
    @NotEmpty(message = "{email.notnull}")
    @Column(unique = true)
    protected String email;

    @NotEmpty(message = "{password.notnull}")
    protected String password;

    protected String address;

    protected String phone;

    protected String photo;

    @ManyToOne
    @JoinColumn(name = "role_id")
    protected Role role;

    @CreationTimestamp
    protected Date creationDate;

    @UpdateTimestamp
    protected Date updateDate;

    protected boolean softDelete = false;

    protected boolean isBanned = false;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "geocode_id")
    protected GeoCode geoCode;

}

