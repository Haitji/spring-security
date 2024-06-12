package spring.security.example.spring_security.services;

import java.util.List;
import java.util.Optional;

import spring.security.example.spring_security.entyties.Product;

public interface ProductService {

    Product save(Product product);

    List<Product> getAllProduct();

    Product getProductById(Long id);

    Product updateProduct(Product product,Long id);

    void deleteProduct(Long id);
}
