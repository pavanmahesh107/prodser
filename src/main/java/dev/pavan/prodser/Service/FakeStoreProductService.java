package dev.pavan.prodser.Service;


import dev.pavan.prodser.dtos.CreateProductRequestDto;
import dev.pavan.prodser.dtos.FakeStoreProductDto;
import dev.pavan.prodser.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
                "https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto productDto = responseEntity.getBody();
            return productDto.toProduct();
        } else {
            throw new RuntimeException("Failed to fetch product with ID: " + id);
        }
    }

    @Override
    public Product createProduct(CreateProductRequestDto request) {
        return null;
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


}
