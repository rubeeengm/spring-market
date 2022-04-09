package com.proday.market.persistence.mapper;

import com.proday.market.domain.Purchase;
import com.proday.market.persistence.entity.Compra;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Ruben Malaga
 */
@Mapper(componentModel = "spring", uses = {PurchaseItenMapper.class})
public interface PurchaseMapper {

    @Mappings({
        @Mapping(source = "idCompra", target = "purchaseId")
        , @Mapping(source = "idCliente", target = "clientId")
        , @Mapping(source = "fecha", target = "date")
        , @Mapping(source = "medioPago", target = "paymentMethod")
        , @Mapping(source = "comentario", target = "comment")
        , @Mapping(source = "estado", target = "state")
        , @Mapping(source = "productos", target = "items")
    })
    public Purchase toPurchase(Compra compra);

    public List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    public Compra toCompra(Purchase purchase);
}
