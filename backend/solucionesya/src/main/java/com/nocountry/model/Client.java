package com.nocountry.model;

import com.nocountry.list.ERoleName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
public class Client extends User {

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERoleName role = ERoleName.ROLE_CLIENT;

    // RELATION CLIENT --> PROVISION
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @ToString.Exclude
    private List<Provision> provisions;*/

    // RELATION CLIENT --> IMAGE
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Image image;

    // RELATION CLIENT --> CONTRACT
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Contract contract;

    // RELATION CLIENT --> OPINION
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @ToString.Exclude
    private List<Opinion> opinions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getId() != null && Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}