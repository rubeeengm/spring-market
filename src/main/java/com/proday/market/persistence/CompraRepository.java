package com.proday.market.persistence;

import com.proday.market.domain.Purchase;
import com.proday.market.domain.repository.PurchaseRepository;

import com.proday.market.persistence.crud.CompraCrudRepository;
import com.proday.market.persistence.entity.Compra;
import com.proday.market.persistence.mapper.PurchaseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return this.mapper.toPurchases(
            (List<Compra>) this.compraCrudRepository.findAll()
        );
    }

    @Override
    public Optional<Purchase> getPurchase(int purchaseId) {
        return this.compraCrudRepository.findById(purchaseId).map(
            compra -> mapper.toPurchase(compra)
        );
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return this.compraCrudRepository.findByIdCliente(clientId)
                    .map(compras -> mapper.toPurchases(compras))
        ;
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = this.mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(
            this.compraCrudRepository.save(compra)
        );
    }

    @Override
    public void delete(int purchaseId) {
        this.compraCrudRepository.deleteById(purchaseId);
    }
}
