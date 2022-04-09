package com.proday.market.domain.service;

import com.proday.market.domain.Purchase;
import com.proday.market.domain.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return this.purchaseRepository.getAll();
    }

    public Optional<Purchase> getPurchase(int purchaseId) {
        return this.purchaseRepository.getPurchase(purchaseId);
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return this.purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return this.purchaseRepository.save(purchase);
    }

    public boolean delete(int purchaseId) {
        return this.getPurchase(purchaseId).map(
            purchase -> {
                this.purchaseRepository.delete(purchaseId);

                return true;
            }
        ).orElse(false);
    }
}
