package com.dbbyte;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.dbbyte.model.Product;
import com.dbbyte.model.ProductCategory;
import com.dbbyte.repository.ProductCategoryRepository;

@SpringBootApplication
public class SbMysqlApplication  implements CommandLineRunner {
	
    private static final Logger logger = LoggerFactory.getLogger(SbMysqlApplication.class);

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    

	public static void main(String[] args) {
		SpringApplication.run(SbMysqlApplication.class, args);
	}
	

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        // save a couple of categories
        ProductCategory categoryA = new ProductCategory("Category A");
        Set productAs = new HashSet<Product>(){{
            add(new Product("Product A1", categoryA));
            add(new Product("Product A2", categoryA));
            add(new Product("Product A3", categoryA));
        }};
        categoryA.setProducts(productAs);

        ProductCategory categoryB = new ProductCategory("Category B");
        Set productBs = new HashSet<Product>(){{
            add(new Product("Product B1", categoryB));
            add(new Product("Product B2", categoryB));
            add(new Product("Product B3", categoryB));
        }};
        categoryB.setProducts(productBs);

        productCategoryRepository.save(new HashSet<ProductCategory>() {{
            add(categoryA);
            add(categoryB);
        }});

        // fetch all categories
        for (ProductCategory productCategory : productCategoryRepository.findAll()) {
            logger.info(productCategory.toString());
        }
    }
    
}
