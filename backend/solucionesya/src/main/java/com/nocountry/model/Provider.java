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

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@RequiredArgsConstructor
@Table(name = "providers")
@AttributeOverride(name = "id", column = @Column(name = "provider_id"))
public class Provider extends User {


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERoleName role = ERoleName.ROLE_PROVIDER;

    // RELATION PROVIDER --> PROVISION
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    @ToString.Exclude
    private List<Provision> provisions;

    // RELATION PROVIDER --> OPINION
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    @ToString.Exclude
    private List<Opinion> opinions;

    // RELATION PROVIDER --> IMAGE
    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Image image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Provider provider = (Provider) o;
        return getId() != null && Objects.equals(getId(), provider.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}