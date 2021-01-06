package com.pocket.pos.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pocket.pos.controller.BillSecondPartyController;
import com.pocket.pos.model.BillSecondParty;
@Component
public class BillSecondPartyAssembler implements RepresentationModelAssembler<BillSecondParty, EntityModel<BillSecondParty>> {

	@Override
	public EntityModel<BillSecondParty> toModel(BillSecondParty entity) {
		return EntityModel.of(entity,
				linkTo(methodOn(BillSecondPartyController.class).getNotDeletedParties(null)).withRel("all"));
	}
	
	

}
