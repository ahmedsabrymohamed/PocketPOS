package com.pocket.pos.assembler;

import com.pocket.pos.controller.ProductController;
import com.pocket.pos.entity.Product;
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

public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductController.class).getNotDeletedProducts(null)).withRel("all"));
    }

    @Override
    public CollectionModel<EntityModel<Product>> toCollectionModel(Iterable<? extends Product> entities) {
        Page<Product> ePage = (Page<Product>) entities;

        List<EntityModel<Product>> emList = new ArrayList<EntityModel<Product>>();
        for (Product product : ePage.getContent()) {
            emList.add(this.toModel(product)
                    .add(linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel()));
        }

        PagedModel<EntityModel<Product>> model = PagedModel.of(emList,
                new PagedModel.PageMetadata(ePage.getSize(), ePage.getNumber(), ePage.getTotalElements(),
                        ePage.getTotalPages()),
                linkTo(methodOn(ProductController.class).getNotDeletedProducts(Optional.of(ePage.getNumber()))).withSelfRel());

        if (ePage.hasNext()) {
            model.add(linkTo(methodOn(ProductController.class).getNotDeletedProducts(Optional.of(ePage.getNumber() + 1))).withRel("next"));
        }

        if (ePage.hasPrevious()) {
            model.add(linkTo(methodOn(ProductController.class).getNotDeletedProducts(Optional.of(ePage.getNumber() + 1))).withRel("prev"));
        }

        return model;
    }
}
