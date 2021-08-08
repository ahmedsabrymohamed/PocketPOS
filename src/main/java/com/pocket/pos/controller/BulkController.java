package com.pocket.pos.controller;

import com.pocket.pos.assembler.BillSecondPartyOnlyAssembler;
import com.pocket.pos.assembler.BillWithoutRelationsAssembler;
import com.pocket.pos.assembler.BulkAssembler;
import com.pocket.pos.model.BulkModel;
import com.pocket.pos.projection.BillWithoutRelationsProjection;
import com.pocket.pos.service.BillService;
import com.pocket.pos.service.BulkService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping("/bulks")
public class BulkController {

    private final BulkService bulkService;
    private final PagedResourcesAssembler<BulkModel> pagedResourcesAssembler;
    private final BulkAssembler bulkAssembler;


    public BulkController(BulkService bulkService,
                          BulkAssembler bulkAssembler,
                          PagedResourcesAssembler<BulkModel> pagedResourcesAssembler) {
        this.bulkService = bulkService;
        this.bulkAssembler = bulkAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;

    }

    @GetMapping()
    public PagedModel<EntityModel<BulkModel>> getBulksByProductId( @RequestParam("productId") Optional<Long> productId,
            @RequestParam("page") Optional<Integer> page) {


        return null;

    }

    @GetMapping("/{id}")
    public EntityModel<BulkModel> getBulkById(@PathVariable("id") Long id) {



        return null;
    }
}
