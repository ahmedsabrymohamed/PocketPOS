package com.pocket.pos.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pocket.pos.controller.BillController;
import com.pocket.pos.projections.BillSecondPartyOnlyProjection;

@Component
public class BillSecondPartyOnlyAssembler implements
		RepresentationModelAssembler<BillSecondPartyOnlyProjection, EntityModel<BillSecondPartyOnlyProjection>> {
	@Override
	public EntityModel<BillSecondPartyOnlyProjection> toModel(BillSecondPartyOnlyProjection entity) {

		return EntityModel.of(entity, linkTo(methodOn(BillController.class).getNotDeletedBills(null)).withRel("all"),
				linkTo(methodOn(BillController.class).getBillSecondParty(entity.getId())).withRel("secondParty"));
	}

}
