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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{category.notnull}")
    @Column(unique = true)
    private String name;

    @Nullable
    private String description;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;
}
