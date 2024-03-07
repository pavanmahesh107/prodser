package dev.pavan.prodser.Service;


import dev.pavan.prodser.dtos.FakeStoreProductDto;
import dev.pavan.prodser.models.Product;
import org.assertj.core.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {


    //Implementation by FakeStoreProductService: The FakeStoreProductService class implements the ProductService interface,
    // which means it provides concrete implementations for all the methods defined in ProductService.
    // This class is responsible for interacting with the FakeStore API to fetch and manipulate product data.

    private RestTemplate restTemplate;


    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    // the FakeStoreProductService class to use a RestTemplate instance for making HTTP requests to the FakeStore API.
    // The RestTemplate instance is injected into the FakeStoreProductService
    // class using constructor injection, making the FakeStoreProductService class more flexible and easier to test.


    //ResponseEntity is a class in Spring Framework that represents the entire HTTP response, including
    //the status code, headers, and body. It allows you to control the HTTP response that your controller method sends back to the client.

    @Override
    public Product getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        //Hey restTemplate please make a call to this particular URL(  "https://fakestoreapi.com/products/" + id, ),
        // when you will make a call you will get a json
        //convert that json into an object of FakeStoreProductDto.class and return that object.
        //but in this case im using the responseEntity object.

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto productDto = responseEntity.getBody();
            return productDto.toProduct();
        } else {
            throw new RuntimeException("Failed to fetch product with ID: " + id);
        }
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products", //url
                fakeStoreProductDto, //request body
                FakeStoreProductDto.class); //data type of response (response json converting the json response into an object this class)


        return response.toProduct();
    }

    @Override
    public Product updateProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> responseEntity =restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto productDto = responseEntity.getBody();
            return productDto.toProduct();
        } else {
            throw new RuntimeException("Failed to update product with ID: " + id);
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto productDto = responseEntity.getBody();
            return productDto.toProduct();
        } else {
            throw new RuntimeException("Failed to delete product with ID: " + id);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto[] productDtos = responseEntity.getBody();

            // Convert FakeStoreProductDtos to Products

            List<Product> products = new ArrayList<>();
            assert productDtos != null;
            for (FakeStoreProductDto productDto : productDtos) {
                products.add(productDto.toProduct());
            }
            return products;
        } else {
            // Handle the case where the request was not successful
            // You can throw an exception, log an error, or return an empty list
            return Collections.emptyList();
        }
    }

    @Override
    public List<Object> getAllCategories() {
       ResponseEntity<String[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                String[].class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(responseEntity.getBody());
        } else {
            // Handle error cases, e.g., throw an exception or log an error message
            throw new RuntimeException("Failed to fetch categories");
        }
    }

    @Override
    public List<Product> getParticularCategory(String category) {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductDto[].class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto[] productDtos = responseEntity.getBody();

            // Convert FakeStoreProductDtos to Products

            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto productDto : productDtos) {
                products.add(productDto.toProduct());
            }
            return products;
        } else {
            // Handle error cases, e.g., throw an exception or log an error message
            throw new RuntimeException("Failed to fetch products in category: " + category);
        }
    }

}


