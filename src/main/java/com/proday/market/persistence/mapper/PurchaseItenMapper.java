package com.proday.market.persistence.mapper;

import com.proday.market.domain.PurchaseItem;
import com.proday.market.persistence.entity.ComprasProducto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Ruben Malaga
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItenMapper {

    @Mappings({
        @Mapping(source = "id.idProducto", target = "productId")
        , @Mapping(source = "cantidad", target = "quantity")
        , @Mapping(source = "estado", target = "active")
    })
    public PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "compra", ignore = true)
        , @Mapping(target = "producto", ignore = true)
        , @Mapping(target = "id.idCompra", ignore = true)
    })
    public ComprasProducto toComprasProducto(PurchaseItem item);
}
