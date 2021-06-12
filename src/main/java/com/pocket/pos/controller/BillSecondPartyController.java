package com.pocket.pos.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pocket.pos.model.BillSecondParty;
import com.pocket.pos.model.assembler.BillSecondPartyAssembler;
import com.pocket.pos.service.BillSecondPartyService;

@CrossOrigin
@RestController
@RequestMapping("/secondparties")
public class BillSecondPartyController {

	private final BillSecondPartyService secondPartyService;
	private final BillSecondPartyAssembler assembler;
	private final PagedResourcesAssembler<BillSecondParty> pagedResourcesAssembler;


	public BillSecondPartyController(BillSecondPartyService secondPartyService, BillSecondPartyAssembler assembler,
			PagedResourcesAssembler<BillSecondParty> pagedResourcesAssembler) {

		this.secondPartyService = secondPartyService;
		this.assembler = assembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@GetMapping("/{id}")
	public EntityModel<BillSecondParty> getsecondPartyById(@PathVariable("id") Long id) {
		BillSecondParty scParty = secondPartyService.getById(id);
		return assembler.toModel(scParty).add(
				linkTo(methodOn(BillSecondPartyController.class).getsecondPartyById(scParty.getId())).withSelfRel());
	}

	@GetMapping
	public PagedModel<EntityModel<BillSecondParty>> getNotDeletedParties(@RequestParam("page") Optional<Integer> page) {

		Page<BillSecondParty> secondParties = secondPartyService.getNotDeletedParties(page.orElse(0));

		return pagedResourcesAssembler.toModel(secondParties, assembler);
	}
}
