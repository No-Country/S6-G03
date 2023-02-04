package com.nocountry.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "opinions")
public class Opinion {

    @Id
    @Column(name = "opinion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{title.notnull}")
    private String title;

    @NotEmpty(message = "{description.opinion.notnull}")
    private String description;

    @NotNull(message = "{rating.opinion.notnull}")
    private Integer rating;

    @OneToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;

    private boolean softDelete = false;

}
