package com.nocountry.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "providers")
@AttributeOverride(name="id", column=@Column(name="provider_id"))
public class Provider extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Service> services;
}
