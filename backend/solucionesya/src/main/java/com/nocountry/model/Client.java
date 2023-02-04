package com.nocountry.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@AttributeOverride(name="id", column=@Column(name="client_id"))
public class Client extends User {

}
