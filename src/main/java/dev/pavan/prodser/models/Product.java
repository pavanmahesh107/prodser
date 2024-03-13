package dev.pavan.prodser.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel {
    //Models are an essential part of any application as they represent the structure of the data used in the application.
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Category category;

}
