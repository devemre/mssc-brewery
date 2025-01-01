package com.devemre.msscbrewery.web.controller;

import com.devemre.msscbrewery.services.CustomerService;
import com.devemre.msscbrewery.web.model.CustomerDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getBeer(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity handleUpdate(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerId, customerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomer(customerId);
    }
}
