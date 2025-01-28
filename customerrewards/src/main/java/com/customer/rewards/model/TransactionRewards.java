package com.customer.rewards.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "rewards")
public class TransactionRewards {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "custid")
	private String customerId;
	
	@Column(name = "name")
	private String customerName;

	@Column(name = "amount")
	private Double transactionAmount;
	
	@Column(name = "rewards")
	private Integer rewards;
	
	@Column(name = "transactiondate")
	private LocalDate transactionDate;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Integer getRewards() {
		return rewards;
	}

	public void setRewards(Integer rewards) {
		this.rewards = rewards;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionRewards(String customerName, LocalDate transactionDate, Integer rewards) {
		this.customerName = customerName;
		this.rewards = rewards;
		this.transactionDate = transactionDate;
	}
	
	public TransactionRewards() {
		
	}
	

}
