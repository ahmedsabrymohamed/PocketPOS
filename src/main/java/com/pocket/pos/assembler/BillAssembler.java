package com.pocket.pos.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pocket.pos.controller.BillController;
import com.pocket.pos.projection.BillProjection;

@Component
public class BillAssembler<T extends BillProjection>
		implements RepresentationModelAssembler<BillProjection, EntityModel<T>> {

	@Override
	public EntityModel<T> toModel(BillProjection entity) {
		T projection = (T) entity;
		return EntityModel.of(projection,
				linkTo(methodOn(BillController.class).getNotDeletedBills(null)).withRel("all"),
				linkTo(methodOn(BillController.class).getBillSecondParty(entity.getId())).withRel("secondParty"));
	}

	@Override
	public CollectionModel<EntityModel<T>> toCollectionModel(Iterable<? extends BillProjection> entities) {
		Page<T> ePage = (Page<T>) entities;

		List<EntityModel<T>> emList = new ArrayList<EntityModel<T>>();
		for (T bill : ePage.getContent()) {
			emList.add(this.toModel(bill)
					.add(linkTo(methodOn(BillController.class).getBillById(bill.getId())).withSelfRel()));
		}
		
		PagedModel<EntityModel<T>> model = PagedModel.of(emList,
				new PagedModel.PageMetadata(ePage.getSize(), ePage.getNumber(), ePage.getTotalElements(),
						ePage.getTotalPages()),
				linkTo(methodOn(BillController.class).getNotDeletedBills(Optional.of(ePage.getNumber()))).withSelfRel());
		
		if(ePage.hasNext()) {
			model.add(linkTo(methodOn(BillController.class).getNotDeletedBills(Optional.of(ePage.getNumber()+1))).withRel("next"));
		}
		
		if(ePage.hasPrevious()) {
			model.add(linkTo(methodOn(BillController.class).getNotDeletedBills(Optional.of(ePage.getNumber()+1))).withRel("prev"));
		}
		
		return model;
	}

}
