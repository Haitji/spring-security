package spring.security.example.spring_security.services.implem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.security.example.spring_security.entyties.Product;
import spring.security.example.spring_security.exceptions.ProductNotFoundException;
import spring.security.example.spring_security.repositories.ProductRepository;
import spring.security.example.spring_security.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @Transactional
    @Override
    public Product updateProduct(Product product,Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product p = optionalProduct.get();
            p.setId(id);
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            return productRepository.save(p);
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    

}
