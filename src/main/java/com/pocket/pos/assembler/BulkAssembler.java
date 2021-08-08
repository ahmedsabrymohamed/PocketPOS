package com.pocket.pos.assembler;

import com.pocket.pos.controller.BulkController;
import com.pocket.pos.controller.ProductController;
import com.pocket.pos.model.BulkModel;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class BulkAssembler implements RepresentationModelAssembler<BulkModel, EntityModel<BulkModel>> {
    @Override
    public EntityModel<BulkModel> toModel(BulkModel entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(BulkController.class).getBulksByProductId(Optional.of(entity.getProductId()), null)).withRel("all"));
    }




}