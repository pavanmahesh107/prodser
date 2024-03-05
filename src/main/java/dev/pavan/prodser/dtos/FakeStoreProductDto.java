package dev.pavan.prodser.dtos;


import dev.pavan.prodser.models.Category;
import dev.pavan.prodser.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FakeStoreProductDto {


    //This class represents product received from the FakeStore API. This Variables will match
    //the structure of a product in the FakeStore API response.

    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;


    //The toProduct() method is a conversion method that converts an instance of FakeStoreProductDto into an instance of your Product class.

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setImageUrl(image);
        product.setDescription(description);
        product.setPrice(price);

        Category productcategory = new Category();
        productcategory.setTitle(category);

        product.setCategory(productcategory);

        return product;

    }


}
