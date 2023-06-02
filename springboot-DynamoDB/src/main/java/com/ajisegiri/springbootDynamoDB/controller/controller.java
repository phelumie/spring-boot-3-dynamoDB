package com.ajisegiri.springbootDynamoDB.controller;

import com.ajisegiri.springbootDynamoDB.repo.CustomerRepository;
import com.ajisegiri.springbootDynamoDB.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class controller {
    private final CustomerRepository repository;

    @PostMapping("customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        var result=repository.save(customer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id){
        Optional<Customer> optionalUser = repository.findById(id);
        Customer result = optionalUser.
                orElseThrow(() -> new RuntimeException("No user with id %d"+ id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        var result= StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/customer/{email}/exists")
    public ResponseEntity<Boolean> checkCustomerExistsByEmail(@PathVariable("email") String email) {
        boolean exists = repository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") String id,@RequestBody Customer customer){
        Optional<Customer> optionalUser = repository.findById(id);

        Customer result = optionalUser.
                orElseThrow(() -> new RuntimeException(String.format("No user with id %d", id)));

        result=customer;
        repository.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
