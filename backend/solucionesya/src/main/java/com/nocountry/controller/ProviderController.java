package com.nocountry.controller;

import com.nocountry.dto.ProviderRequest;
import com.nocountry.dto.ProviderResponse;
import com.nocountry.exception.UserException;
import com.nocountry.service.IProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderResponse>> getAllProviders() throws UserException {
        return ResponseEntity.ok(providerService.getAllProviders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getById(@PathVariable(name = "id") String id) throws UserException {
        return ResponseEntity.ok(providerService.getById(id));
    }

    @PostMapping("/new-provider")
    public ResponseEntity<ProviderResponse> createProvider(@Valid @RequestBody ProviderRequest providerRequest) throws UserException {
        ProviderResponse providerResponse = providerService.createProvider(providerRequest);
        return new ResponseEntity<>(providerResponse, HttpStatus.CREATED);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<ProviderResponse> modifyProvider(@Valid @RequestBody ProviderRequest providerRequest, @PathVariable(name = "id")String id) throws UserException {
        ProviderResponse providerResponse = providerService.modifyProvider(providerRequest, id);
        return ResponseEntity.ok(providerResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable(name = "id") String id) throws UserException {
        providerService.deleteProvider(id);
        return new ResponseEntity<>("PROVIDER DELETED", HttpStatus.OK);
    }

}
