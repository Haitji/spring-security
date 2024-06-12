package spring.security.example.spring_security.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import spring.security.example.spring_security.entyties.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{

    List<Product> findAll();

    void deleteById(Long id);
}
