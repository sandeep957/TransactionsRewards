package com.customer.rewards.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.rewards.model.TransactionRewards;

@Repository
public interface RewardsRepository extends JpaRepository<TransactionRewards, Long> {
	
	List<TransactionRewards> findByTransactionDateGreaterThanEqual(LocalDate startDate);
}
