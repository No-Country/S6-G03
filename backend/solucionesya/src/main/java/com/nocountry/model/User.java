package com.nocountry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class User  {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

//    @NotEmpty(message = "{firstname.notnull}")
    @Column(name = "first_name")
    protected String firstName;

//    @NotEmpty(message = "{lastname.notnull}")
    @Column(name = "last_name")
    protected String lastName;

    @Email(message = "{email.pattern}")
    @NotEmpty(message = "{email.notnull}")
    @Column(unique = true)
    protected String email;

    @NotEmpty(message = "{password.notnull}")
    protected String password;

    protected String address;

    protected String phone;

    @Column(name = "profile_photo")
    protected String profilePhoto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    protected Date creationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    protected Date updateDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "geocode_id")
    protected GeoCode geoCode;

    @Column(name = "is_banned", nullable = false)
    protected boolean isBanned = Boolean.FALSE;

    @Column(name = "soft_delete", nullable = false)
    protected boolean softDelete = Boolean.FALSE;

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}