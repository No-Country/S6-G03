package com.nocountry.model;

import com.nocountry.list.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{rolename.notnull}")
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Nullable
    private String description;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;

}