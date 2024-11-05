package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Category;
import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.repository.CategoryRepository;
import ee.gert.veebipood.repository.ProductRepository;
import ee.gert.veebipood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller should be super short, everything that isn't an api enpoint should be moved to "service"

@RestController // annotation @ -> sellega votab controller  api requeste vastu
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

//    @Autowired
//    private ProductRepository productRepository;

    @GetMapping("categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @PostMapping("categories")
    public ResponseEntity<List<Category>> addCategory(@RequestBody Category category){
        categoryRepository.save(category);
        return ResponseEntity.status(201).body(categoryRepository.findAll());
    }

    @GetMapping("category-products/{categoryId}")
    public ResponseEntity<List<Product>>  getCategoryProducts(@PathVariable Long categoryId){
        return ResponseEntity.ok().body(productRepository.findByCategory_Id(categoryId));
    }

    
    @GetMapping("protein-category-products/{categoryId}")
    public ResponseEntity<Integer> getProteinCategoryProducts(@PathVariable Long categoryId){
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return ResponseEntity.ok().body(categoryService.getProductListProteinCount(products));
    }



//    /category?categoryId=1&productId=2
//    @PatchMapping("category")
//    public List<Category> addProductToCategory(@RequestParam Long categoryId,
//                                               @RequestParam Long productId){
//        Product product = prodcutRepository.findById(productId).get();
//        Category category = categoryRepository.findById(categoryId).get();
//        List<Product> productsInCategory = category.getProducts();
//
//        productsInCategory.add(product);
//
//        categoryRepository.save(category);
//        return categoryRepository.findAll();
//    }
}
