package com.pocket.pos.util.mapper.entitymapper;

import com.pocket.pos.entity.Bill;
import com.pocket.pos.model.BillModel;
import com.pocket.pos.projection.BillProjection;
import com.pocket.pos.projection.BillSecondPartyOnlyProjection;
import com.pocket.pos.projection.BillWithoutRelationsProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {BillSecondPartyMapper.class,BillItemMapper.class})
public interface BillMapper {


    @Mapping(source = "id",target = "billId")
    BillModel mapBillEntityToModel(Bill bill);

    @Mapping(source = "id",target = "billId")
    BillModel mapBillSecondPartyOnlyProjectionToModel(BillSecondPartyOnlyProjection bill);

    @Mapping(source = "id",target = "billId")
    BillModel mapBillProjectionToModel(BillProjection bill);

    @Mapping(source = "id",target = "billId")
    BillModel mapBillWithoutRelationsProjectionToModel(BillWithoutRelationsProjection bill);

    Collection<BillModel> mapBillEntitiesToModels(Collection<Bill> bills);
    List<BillModel> mapBillEntitiesToModels(List<Bill> bills);

    Collection<BillModel> mapBillSecondPartyOnlyProjectionsToModels(Collection<BillSecondPartyOnlyProjection> bills);
    List<BillModel> mapBillSecondPartyOnlyProjectionsToModels(List<BillSecondPartyOnlyProjection> bills);

    Collection<BillModel> mapBillWithoutRelationsProjectionsToModels(Collection<BillWithoutRelationsProjection> bills);
    List<BillModel> mapBillWithoutRelationsProjectionsToModels(List<BillWithoutRelationsProjection> bills);



}
