package com.pocket.pos.controller;

import com.pocket.pos.assembler.BulkAssembler;
import com.pocket.pos.assembler.ProductAssembler;
import com.pocket.pos.entity.Product;
import com.pocket.pos.exception.ApiSimpleResponse;
import com.pocket.pos.model.BillModel;
import com.pocket.pos.model.BulkModel;
import com.pocket.pos.model.ProductModel;
import com.pocket.pos.service.ProductService;
import com.pocket.pos.util.ResponseEntityBuilder;
import com.pocket.pos.util.mapper.entitymapper.BulkMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping("/Products")
public class ProductController {

    private final ProductService productService;
    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;
    private final ProductAssembler productAssembler;


    public ProductController(ProductService productService, PagedResourcesAssembler<Product> pagedResourcesAssembler, ProductAssembler productAssembler) {
        this.productService = productService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.productAssembler = productAssembler;

    }

    @GetMapping
    public PagedModel<EntityModel<Product>> getNotDeletedProducts(
            @RequestParam("page") Optional<Integer> page) {

        Page<Product> products = productService.getNotDeletedProducts(page.orElse(0));

        return pagedResourcesAssembler.toModel(products, productAssembler);

    }

    @GetMapping("/{id}")
    public EntityModel<Product> getProductById(@PathVariable("id") Long id) {

        Product product = productService.getById(id);

        return productAssembler.toModel(product)
                .add(linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel());
    }




    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductModel product) {
        Long id = productService.addProduct(product).getId();
        return ResponseEntity //
                .created(linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel().toUri())
                .body(ResponseEntityBuilder
                        .build(new ApiSimpleResponse(HttpStatus.CREATED, "the Product Created Successfully")));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductModel product) {
        product.setProductId(id);
        //productService.updateBill(product);
        return ResponseEntity //
                .created(linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel().toUri())
                .body(ResponseEntityBuilder
                        .build(new ApiSimpleResponse(HttpStatus.ACCEPTED, "the Product Updated Successfully")));

    }

}
