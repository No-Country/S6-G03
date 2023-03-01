package com.nocountry.dto.response;

import com.nocountry.list.ERoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private String email;

    private ERoleName role;

    private String token;
}
