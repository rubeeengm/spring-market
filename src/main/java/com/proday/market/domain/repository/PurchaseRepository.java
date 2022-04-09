package com.proday.market.domain.repository;

import com.proday.market.domain.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
public interface PurchaseRepository {

    public List<Purchase> getAll();

    public Optional<Purchase> getPurchase(int purchaseId);

    public Optional<List<Purchase>> getByClient(String clientId);

    public Purchase save(Purchase purchase);

    public void delete(int purchaseId);
}
