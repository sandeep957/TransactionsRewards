package com.customer.rewards.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.model.TransactionDetails;
import com.customer.rewards.service.TransactionRewardsService;


@RestController
public class RewardsController {

   
    private final TransactionRewardsService service;

    public RewardsController(TransactionRewardsService service) {
    	this.service = service;
    }
    
    @GetMapping("/transaction/details")
    public ResponseEntity<Map<String, Map<String, Integer>>> getEmployees() {
        try {
            return new ResponseEntity<>(service.getLastThreeMonthsRewards(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transaction/save")
    public ResponseEntity<String> newEmployee(@RequestBody TransactionDetails details) {
        
    	service.saveTransactionDetails(details);
        return new ResponseEntity<>("Transaction details saved successfully", HttpStatus.OK);
    }


}
