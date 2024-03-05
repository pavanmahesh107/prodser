package dev.pavan.prodser.Controller;

import dev.pavan.prodser.Service.ProductService;
import dev.pavan.prodser.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

          private ProductService productService;


          //It is specifying that the productService field will
         // hold an instance of the ProductService class.
        //This means that productService will be an object of type ProductService,
       // which can be used to access methods and properties defined in the ProductService class.

    @Autowired
    public ProductController(ProductService productService){

        this.productService = productService;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}