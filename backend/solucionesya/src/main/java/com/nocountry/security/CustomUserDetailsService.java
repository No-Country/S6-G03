package com.nocountry.security;


import com.nocountry.dto.request.RegisterResquest;
import com.nocountry.dto.response.ClientLoginResponse;
import com.nocountry.dto.response.ProviderLoginResponse;
import com.nocountry.dto.response.RegisterResponse;
import com.nocountry.dto.response.UserResponse;
import com.nocountry.exception.UserException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.list.ERoleName;
import com.nocountry.mapper.Mapper;
import com.nocountry.model.Client;
import com.nocountry.model.Provider;
import com.nocountry.repository.IClientRepository;
import com.nocountry.repository.IProviderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final Mapper mapper;

    private final IProviderRepository providerRepository;

    private final IClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtGenerator jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Provider> providerOptional = providerRepository.findByEmail(email);
        if (providerOptional.isPresent()) {
            Provider provider = providerOptional.get();
            return new org.springframework.security.core.userdetails.User(provider.getEmail(), provider.getPassword(),
                    mapRolesToAuthorities(Arrays.asList(provider.getRole())));
        }
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return new org.springframework.security.core.userdetails.User(client.getEmail(), client.getPassword(),
                    mapRolesToAuthorities(Arrays.asList(client.getRole())));
        }
        throw new UsernameNotFoundException("Username not found");
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<ERoleName> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
    }

    public RegisterResponse saveProvider(@Valid RegisterResquest registerDto) throws UserException {
        if (providerRepository.existsByEmail(registerDto.getEmail()) ||
                clientRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(registerDto.getEmail()));
        }
        Provider user = mapper.getMapper().map(registerDto, Provider.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(ERoleName.ROLE_PROVIDER);
        Provider userSaved = providerRepository.save(user);
        String token = this.authenticated(registerDto);
        RegisterResponse registerResponse = mapper.getMapper().map(userSaved, RegisterResponse.class);
        registerResponse.setToken(token);

        return registerResponse;
    }

    public RegisterResponse saveClient(@Valid RegisterResquest registerDto) throws UserException {
        if (providerRepository.existsByEmail(registerDto.getEmail()) ||
                clientRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(registerDto.getEmail()));
        }
        Client user = mapper.getMapper().map(registerDto, Client.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(ERoleName.ROLE_CLIENT);
        Client userSaved = clientRepository.save(user);
        String token = this.authenticated(registerDto);
        RegisterResponse registerResponse = mapper.getMapper().map(userSaved, RegisterResponse.class);
        registerResponse.setToken(token);

        return registerResponse;
    }

    public UserResponse getAuthenticated() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (providerRepository.existsByEmail(email)) {
            UserResponse userDto = mapper.getMapper().map(providerRepository.findByEmail(email), UserResponse.class);
            return userDto;
        }
        if (clientRepository.existsByEmail(email)) {
            UserResponse userDto = mapper.getMapper().map(clientRepository.findByEmail(email), UserResponse.class);
            return userDto;
        }
        throw new UserException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(email));
    }

//    public LoginResponse findByEmail(String email) {
//        Optional<Provider> provider = providerRepository.findByEmail(email);
//        if (provider.isPresent()) {
//            return mapper.getMapper().map(provider, ProviderLoginResponse.class);
//        }
//        Optional<Client> client = clientRepository.findByEmail(email);
//        if (client.isPresent()) {
//            return mapper.getMapper().map(provider, ClientLoginResponse.class);
//        }
//        throw new ResourceNotFoundException("Email not found");
//    }

    private String authenticated(RegisterResquest requestUserDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestUserDto.getEmail(), requestUserDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(authentication);
        return token;
    }

    public ProviderLoginResponse loginProvider(RegisterResquest loginDto) throws UserException {
        Optional<Provider> providerOptional = providerRepository.findByEmail(loginDto.getEmail());
        if (providerOptional.isPresent()) {
            Provider provider = providerOptional.get();
            ProviderLoginResponse providerLoginResponse = mapper.getMapper().map(provider, ProviderLoginResponse.class);
            providerLoginResponse.setFullName(provider.getFullName());
//            if (client.getImage() != null) {
//                clientResponse.setImage(imageMapper.convertToResponse(entity.getImage()));
//                response.setPathImage(entity.getImage().getPath());
//            }
            String token = this.authenticated(loginDto);
            providerLoginResponse.setToken(token);
            return providerLoginResponse;
        }
        throw new UserException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(loginDto.getEmail()));
    }

    public ClientLoginResponse loginClient(RegisterResquest loginDto) throws UserException {
        Optional<Client> clientOptional = clientRepository.findByEmail(loginDto.getEmail());
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            ClientLoginResponse clientLoginResponse = mapper.getMapper().map(client, ClientLoginResponse.class);
            clientLoginResponse.setFullName(client.getFullName());
//            if (client.getImage() != null) {
//                clientResponse.setImage(imageMapper.convertToResponse(entity.getImage()));
//                response.setPathImage(entity.getImage().getPath());
//            }
            String token = this.authenticated(loginDto);
            clientLoginResponse.setToken(token);
            return clientLoginResponse;
        }
        throw new UserException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(loginDto.getEmail()));
    }
}