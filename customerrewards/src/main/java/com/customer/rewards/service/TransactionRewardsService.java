package com.customer.rewards.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.customer.rewards.model.TransactionDetails;
import com.customer.rewards.model.TransactionRewards;
import com.customer.rewards.repository.RewardsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionRewardsService {

	private final RewardsRepository repository;

	public TransactionRewardsService(RewardsRepository repository) {
		this.repository = repository;
	}

	public Map<String, Map<String, Integer>> getLastThreeMonthsRewards() {
		Map<String, Map<String, Integer>> customerWithMonthRewards = new HashMap<>();
		
		LocalDate start = LocalDate.now().minusMonths(3).withDayOfMonth(1);
		List<TransactionRewards> rewards = repository.findByTransactionDateGreaterThanEqual(start);
		
		Map<String, List<TransactionRewards>> customerRewardsMap = rewards.stream()
				.collect(Collectors.groupingBy(TransactionRewards::getCustomerName));
		
		for (Entry<String, List<TransactionRewards>> entry : customerRewardsMap.entrySet()) {
			if (null != entry.getValue() && !entry.getValue().isEmpty()) {

				Map<String, Integer> monthRewards = new HashMap<>();
				for (TransactionRewards reward : entry.getValue()) {

					String month = reward.getTransactionDate().getMonth().toString();
					if (monthRewards.get(month) != null) {
						monthRewards.put(month, monthRewards.get(month) + reward.getRewards());
					} else {
						monthRewards.put(month, reward.getRewards());
					}
				}
				customerWithMonthRewards.put(entry.getKey(), monthRewards);
			}
		}

		return customerWithMonthRewards;
	}

	public void saveTransactionDetails(TransactionDetails details) {
		try {
			TransactionRewards transactionRewards = new TransactionRewards();
			transactionRewards.setCustomerId(details.getCustomerId());
			transactionRewards.setCustomerName(details.getCustomerName());
			transactionRewards.setTransactionAmount(details.getTransactionAmount());
			transactionRewards.setTransactionDate(LocalDate.now());
			Double transactionAmount = details.getTransactionAmount();
			if (null != transactionAmount) {
				int rewards = 0;
				if (transactionAmount.intValue() > 100) {
					rewards = (transactionAmount.intValue() - 100) * 2;
				}
				if (transactionAmount.intValue() > 50) {

					rewards = rewards + ((transactionAmount.intValue() / 50) - 1) * 50;
				}
				transactionRewards.setRewards(rewards);
			}
			repository.save(transactionRewards);
		} catch (Exception ex) {

			log.error("Exception occurred while saving transaction. "+ex.getLocalizedMessage());
			throw new RuntimeException("Exception occurred while saving transaction. ");
		}
	}
}
