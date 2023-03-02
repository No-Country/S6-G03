package com.nocountry.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResquest {
    private String email;
    private String password;
}
