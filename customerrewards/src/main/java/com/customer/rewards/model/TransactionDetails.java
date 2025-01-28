package com.customer.rewards.model;

import lombok.Data;

@Data
public class TransactionDetails {


	private String customerId;
	
	private String customerName;

	private Double transactionAmount;

	public TransactionDetails(String customerId, String customerName, Double transactionAmount) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.transactionAmount = transactionAmount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public TransactionDetails() {
		
	}
	

	
}
