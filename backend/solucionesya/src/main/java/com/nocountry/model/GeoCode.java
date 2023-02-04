package com.nocountry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "geocodes")
public class GeoCode {
    @Id
    @Column(name = "geocode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float latitude;
    private float longitude;

}
