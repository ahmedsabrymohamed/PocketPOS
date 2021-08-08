package com.pocket.pos.util.mapper.entitymapper;

import com.pocket.pos.entity.BillSecondParty;
import com.pocket.pos.model.BillSecondPartyModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper
public interface BillSecondPartyMapper {

    default Long mapBillSecondPartyToId (BillSecondParty secondParty){
        return secondParty.getId();
    }

    @Mapping(source = "id" , target = "secondPartyId")
    BillSecondPartyModel mapBillSecondPartyEntityToBillSecondPartyModel(BillSecondParty secondParty);

    Collection<BillSecondPartyModel> mapBillSecondPartyEntitiesToBillSecondPartyModels(Collection<BillSecondParty> secondParties);
    List<BillSecondPartyModel> mapBillSecondPartyEntitiesToBillSecondPartyModels(List<BillSecondParty> secondParties);
}
