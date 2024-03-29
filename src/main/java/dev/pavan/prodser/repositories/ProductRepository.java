package dev.pavan.prodser.repositories;

import dev.pavan.prodser.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product p);

    //the Attributes that are automatically
    //generated by DB will not be in the param, but will be there in returned object
}
