package dev.pavan.prodser.Service;

import dev.pavan.prodser.models.Category;
import dev.pavan.prodser.models.Product;
import dev.pavan.prodser.repositories.CategoryRepository;
import dev.pavan.prodser.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("SelfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Product createProduct(String title, String description, String category, double price, String image) {

        Product product = new Product();
        //The line Product product = new Product(); creates a new instance of the Product class.
        // This instance is used to store the data of the product that is being created.

        //When you call new Product(), memory is allocated to store the product's data, and a reference to
        // this memory location is stored in the variable product. This allows you to set the attributes of the Product object
        // (such as title, description, price, and image URL) using the product variable before saving it to the database.

        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        //Check if the category exists in the database by calling

        Category categoryFromDataBase = categoryRepository.findByTitle(category);

        if(categoryFromDataBase == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDataBase = categoryRepository.save(newCategory);
            //categoryFromDataBase.setTitle(title);
        }
        //if the category was found from DB -> categoryFromDataBase will be having an ID
        // else : categoryFromDataBase won't have any ID
        product.setCategory(categoryFromDataBase);

        //Save the Product instance to the database using productRepository.save(product).

        Product savedProduct = productRepository.save(product);

        //If the save operation fails (savedProduct is null), throw a RuntimeException.

        if (savedProduct == null) {
            throw new RuntimeException("Failed to save product");
        }

        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Object> getAllCategories() {
        return null;
    }

    @Override
    public List<Product> getParticularCategory(String category) {
        return null;
    }
}
