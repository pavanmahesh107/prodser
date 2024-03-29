package dev.pavan.prodser.Service;


import dev.pavan.prodser.models.Product;

import java.util.List;

public interface ProductService {

    //It specifies a set of methods that a class implementing the interface must provide.


    //Switching implementations: By using the ProductService interface, you can easily switch the implementation of the service without changing
    // the code that uses the service. For example, if you decide to switch from using the FakeStore API to a different API or a local database,
    // you can create a new implementation of ProductService and use it without modifying the code that uses the ProductService interface.

    Product getProductById(Long id);


    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);

    Product updateProduct(Long id);

    Product deleteProduct(Long id);

    List<Product> getAllProducts();

    List<Object> getAllCategories();

    List<Product> getParticularCategory(String category);
}


