package com.pocket.pos.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pocket.pos.controller.BillController;
import com.pocket.pos.projection.BillWithoutRelationsProjection;

@Component
public class BillWithoutRelationsAssembler implements
		RepresentationModelAssembler<BillWithoutRelationsProjection, EntityModel<BillWithoutRelationsProjection>> {
	@Override
	public EntityModel<BillWithoutRelationsProjection> toModel(BillWithoutRelationsProjection entity) {
		
		return EntityModel.of(entity,
				linkTo(methodOn(BillController.class).getNotDeletedBills(null)).withRel("all"),
				linkTo(methodOn(BillController.class).getBillSecondParty(entity.getId())).withRel("secondParty"));
	}
}
