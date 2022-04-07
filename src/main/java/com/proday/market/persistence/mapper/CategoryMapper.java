package com.proday.market.persistence.mapper;

import com.proday.market.domain.Category;
import com.proday.market.persistence.entity.Categoria;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Ruben Malaga
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
        @Mapping(source = "idCategoria", target = "categoryId")
        , @Mapping(source = "descripcion", target = "category")
        , @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
