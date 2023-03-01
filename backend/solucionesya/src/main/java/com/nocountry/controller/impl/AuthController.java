package com.nocountry.controller.impl;


import com.nocountry.dto.request.RegisterResquest;
import com.nocountry.dto.response.ClientLoginResponse;
import com.nocountry.dto.response.ProviderLoginResponse;
import com.nocountry.dto.response.RegisterResponse;
import com.nocountry.exception.UserException;
import com.nocountry.security.CustomUserDetailsService;
import com.nocountry.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtGenerator jwtGenerator;

    @PostMapping("/register/provider")
    public ResponseEntity<RegisterResponse> registerProvider(@RequestBody RegisterResquest registerDto) throws UserException {
        RegisterResponse newUser = customUserDetailsService.saveProvider(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/register/client")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterResquest registerDto) throws UserException {
        RegisterResponse newUser = customUserDetailsService.saveClient(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login/client")
    public ResponseEntity<ClientLoginResponse> loginClient(@RequestBody RegisterResquest loginDto) throws UserException {
        ClientLoginResponse clientLoginResponse = customUserDetailsService.loginClient(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(clientLoginResponse);
    }

    @PostMapping("/login/provider")
    public ResponseEntity<ProviderLoginResponse> loginProvider(@RequestBody RegisterResquest loginDto) throws UserException {
        ProviderLoginResponse providerLoginResponse = customUserDetailsService.loginProvider(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(providerLoginResponse);
    }

}
