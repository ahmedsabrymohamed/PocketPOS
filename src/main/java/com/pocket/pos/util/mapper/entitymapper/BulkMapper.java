package com.pocket.pos.util.mapper.entitymapper;

import com.pocket.pos.entity.Bulk;
import com.pocket.pos.entity.Product;
import com.pocket.pos.model.BulkModel;
import com.pocket.pos.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface BulkMapper {

    @Mapping(source = "id",target = "bulkId")
    BulkModel mapBulkEntityToBulkModel(Bulk bulk);

    default Long mapBulkEntityToId(Bulk bulk){
        return bulk.getId();
    }

    Collection<BulkModel> mapBulkEntitiesToBulkModels(Collection<Bulk> bulks);
    List<BulkModel> mapBulkEntitiesToBulkModels(List<Bulk> bulks);
}
