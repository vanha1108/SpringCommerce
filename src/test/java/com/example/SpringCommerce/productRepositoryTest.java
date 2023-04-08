package com.example.SpringCommerce;

import com.example.SpringCommerce.product.Product;
import com.example.SpringCommerce.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class productRepositoryTest {
    @Autowired private ProductRepository repo;

    @Test
    public void testAddnew(){
        Product pro = new Product();
        pro.setCategory("NokiaA1");
        pro.setPrice(111111);
        pro.setBrand("Nokia");
        pro.setColor("Pink");

        Product savePro = repo.save(pro);
        Assertions.assertThat(savePro).isNotNull();
        Assertions.assertThat(savePro.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for(Product prod : products){
            System.out.println(prod);
        }
    }
    @Test
    public void testUpdate(){
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product pro = optionalProduct.get();
        pro.setBrand("Vmarts");
        repo.save(pro);

        Product updateProduct = repo.findById(productId).get();
        Assertions.assertThat(updateProduct.getBrand()).isEqualTo("Vmarts");
    }

    @Test
    public void testGet(){
        Integer productId = 2;
        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    @Test
    public void testDelete(){
        Integer productId = 3;
        repo.deleteById(productId);

        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}
