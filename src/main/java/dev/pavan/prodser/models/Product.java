package dev.pavan.prodser.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    //Models are an essential part of any application as they represent the structure of the data used in the application.

    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private Category category;

}
