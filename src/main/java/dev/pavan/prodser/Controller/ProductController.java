package dev.pavan.prodser.Controller;

import dev.pavan.prodser.Service.ProductService;
import dev.pavan.prodser.dtos.CreateProductRequestDto;
import dev.pavan.prodser.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        return productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getImage()
        );
    }
//    @PostMapping("/products")
//    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto request) {
//        Product createdProduct = productService.createProduct(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
//    }

    @PutMapping("/products/{id}")
    public  ResponseEntity<Product> upadateProduct(@PathVariable("id") Long id){
        Product product = productService.updateProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
           Product product = productService.deleteProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> product= productService.getAllProducts();
        if (product != null && !product.isEmpty()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products/categories")
    public ResponseEntity<List<Object>> getAllCategories() {
        List<Object> categories = productService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getParticularCategory(@PathVariable String category) {
        List<Product> products = productService.getParticularCategory(category);
        return ResponseEntity.ok(products);
    }
}
