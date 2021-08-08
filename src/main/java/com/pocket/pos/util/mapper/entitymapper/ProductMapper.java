package com.pocket.pos.util.mapper.entitymapper;

import com.pocket.pos.entity.Product;
import com.pocket.pos.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(source = "id" , target = "productId")
    ProductModel mapProductEntityToProductModel(Product product);

    default Long mapProductEntityToId(Product product){
        return product.getId();
    }

    Collection<ProductModel> mapProductEntitiesToProductModels(Collection<Product> products);

    List<ProductModel> mapProductEntitiesToProductModels(List<Product> products);
}
