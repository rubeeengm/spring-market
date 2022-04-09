package com.proday.market.persistence.crud;

import com.proday.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    public Optional<List<Compra>> findByIdCliente(String idCliente);
}
