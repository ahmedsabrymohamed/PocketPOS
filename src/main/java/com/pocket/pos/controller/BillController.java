package com.pocket.pos.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pocket.pos.exception.ApiSimpleResponse;
import com.pocket.pos.assembler.BillSecondPartyOnlyAssembler;
import com.pocket.pos.assembler.BillWithoutRelationsAssembler;
import com.pocket.pos.projection.BillSecondPartyOnlyProjection;
import com.pocket.pos.projection.BillWithoutRelationsProjection;
import com.pocket.pos.model.BillModel;
import com.pocket.pos.service.BillService;
import com.pocket.pos.util.ResponseEntityBuilder;

@CrossOrigin
@RestController
@RequestMapping("/bills")
public class BillController {

	private final BillService billService;
	private final PagedResourcesAssembler<BillWithoutRelationsProjection> pagedResourcesAssembler;
	private final BillWithoutRelationsAssembler noRelationsAssembler;
	private final BillSecondPartyOnlyAssembler secondPartyOnlyAssembler;

	public BillController(BillService billService, BillWithoutRelationsAssembler noRelationsAssembler,
			BillSecondPartyOnlyAssembler secondPartyOnlyAssembler,
			PagedResourcesAssembler<BillWithoutRelationsProjection> pagedResourcesAssembler) {
		this.billService = billService;
		this.noRelationsAssembler = noRelationsAssembler;
		this.secondPartyOnlyAssembler = secondPartyOnlyAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;

	}

	@GetMapping
	public PagedModel<EntityModel<BillWithoutRelationsProjection>> getNotDeletedBills(
			@RequestParam("page") Optional<Integer> page) {

		Page<BillWithoutRelationsProjection> bills = billService.getNotDeletedBills(page.orElse(0));

		return pagedResourcesAssembler.toModel(bills, noRelationsAssembler);

	}

	@GetMapping("/{id}")
	public EntityModel<BillWithoutRelationsProjection> getBillById(@PathVariable("id") Long id) {

		BillWithoutRelationsProjection bill = billService.getBillById(id);

		return noRelationsAssembler.toModel(bill)
				.add(linkTo(methodOn(BillController.class).getBillById(bill.getId())).withSelfRel());
	}

	@GetMapping("/{id}/secondParty")
	public EntityModel<BillSecondPartyOnlyProjection> getBillSecondParty(@PathVariable("id") Long id) {

		BillSecondPartyOnlyProjection secondPartyOnlyProjection = billService.getSecondPartyById(id);

		return secondPartyOnlyAssembler.toModel(secondPartyOnlyProjection)
				.add(linkTo(methodOn(BillController.class).getBillSecondParty(secondPartyOnlyProjection.getId()))
						.withSelfRel());
	}

	@PostMapping
	public ResponseEntity<?> addBill(@RequestBody BillModel bill) {
		Long id = billService.addBill(bill).getId();
		return ResponseEntity //
				.created(linkTo(methodOn(BillController.class).getBillById(id)).withSelfRel().toUri())
				.body(ResponseEntityBuilder
						.build(new ApiSimpleResponse(HttpStatus.CREATED, "the Bill Created Successfully")));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBill(@PathVariable("id") Long id, @RequestBody BillModel bill) {
		bill.setBillId(id);
		billService.updateBill(bill);
		return ResponseEntity //
				.created(linkTo(methodOn(BillController.class).getBillById(id)).withSelfRel().toUri())
				.body(ResponseEntityBuilder
						.build(new ApiSimpleResponse(HttpStatus.ACCEPTED, "the Bill Updated Successfully")));

	}

}
