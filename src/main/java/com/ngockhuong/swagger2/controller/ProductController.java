package com.ngockhuong.swagger2.controller;

import com.ngockhuong.swagger2.model.Error;
import com.ngockhuong.swagger2.model.Product;
import com.ngockhuong.swagger2.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ProductController {

    @Autowired
    private ProductService fakeProductService;

    @ApiOperation(value = "View a list of available products", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> list(Model model) {
        Iterable<Product> products = fakeProductService.listAllProducts();

        return products;
    }

    @ApiOperation(value = "Search a product with an ID",response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search a product with an ID sucessfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource", response = Error.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found", response = Error.class)
    })
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product showProduct(@PathVariable Integer id, Model model) {
        Product product = fakeProductService.getProductById(id);

        return product;
    }

    @ApiOperation(value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveProduct(@RequestBody Product product) {
        fakeProductService.saveProduct(product);

        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product storedProduct = fakeProductService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());

        fakeProductService.saveProduct(storedProduct);

        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Integer id) {
        fakeProductService.deleteProduct(id);

        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }
}
