package com.pocket.pos.util.mapper.entitymapper;

import com.pocket.pos.entity.BillItem;
import com.pocket.pos.model.BillItemModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {BulkMapper.class,ProductMapper.class})
public interface BillItemMapper {


    @Mapping(source = "bulk" , target = "bulkId")
    @Mapping(source = "bulk.product", target = "productId")
    BillItemModel mapBillItemEntityToBillItemModel(BillItem billItem);



    Collection<BillItemModel> mapBillItemEntitiesToBillItemModels(Collection<BillItem> billItems);
    List<BillItemModel> mapBillItemEntitiesToBillItemModels(List<BillItem> billItems);





}
