package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.Product;
import com.example.SpringCommerce.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> listAll() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product pro) {
        return repo.save(pro);
    }

    public boolean isExistByUserId(int id) {
        Optional<Product> pro = Optional.ofNullable(repo.findById(id).orElse(new Product()));

        return pro.isPresent();
    }

    @Transactional
    public Product updateProduct(Product pro) {
        System.out.println("Updating product with ID: " + pro.getId());

        Optional<Product> optionalProduct = Optional.ofNullable(repo.findById(pro.getId()).orElse(new Product()));
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setCategory(pro.getCategory());
            existingProduct.setPrice(pro.getPrice());
            existingProduct.setColor(pro.getColor());
            existingProduct.setBrand(pro.getBrand());

            return repo.save(existingProduct);
        } else {
            System.err.println("Product not found with ID: " + pro.getId());
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Product> searchProduct(String category, String brand, String color, Double priceFrom, Double priceTo) {
        List<Product> result = repo.findAll();
        if (StringUtils.isNotBlank(category)) {
            List<Product> searchCategory = new ArrayList<>();
            for (Product product : result) {
                if (product.getCategory().equalsIgnoreCase(category)) {
                    searchCategory.add(product);
                }
            }
            if (CollectionUtils.isEmpty(searchCategory)) {
                return searchCategory;
            }
            result = searchCategory;
        }

        if (StringUtils.isNotBlank(brand)) {
            List<Product> searchBrand = new ArrayList<>();
            for (Product product : result) {
                if (product.getBrand().equalsIgnoreCase(brand)) {
                    searchBrand.add(product);
                }
            }
            if (CollectionUtils.isEmpty(searchBrand)) {
                return searchBrand;
            }
            result = searchBrand;
        }

        if (StringUtils.isNotBlank(color)) {
            List<Product> searchColor = new ArrayList<>();
            for (Product product : result) {
                if (product.getColor().equalsIgnoreCase(color)) {
                    searchColor.add(product);
                }
            }
            if (CollectionUtils.isEmpty(searchColor)) {
                return searchColor;
            }
            result = searchColor;
        }

        if (priceFrom != null) {
            List<Product> searchPriceFrom = new ArrayList<>();
            for (Product product : result) {
                if (product.getPrice() >= priceFrom) {
                    searchPriceFrom.add(product);
                }
            }
            if (CollectionUtils.isEmpty(searchPriceFrom)) {
                return searchPriceFrom;
            }
            result = searchPriceFrom;
        }


        if (priceTo != null) {
            List<Product> searchPriceTo = new ArrayList<>();
            for (Product product : result) {
                if (product.getPrice() <= priceTo) {
                    searchPriceTo.add(product);
                }
            }
            if (CollectionUtils.isEmpty(searchPriceTo)) {
                return searchPriceTo;
            }
            result = searchPriceTo;
        }
        return result;
    }

    @Override
    public Product getProductById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found!"));
    }
}
