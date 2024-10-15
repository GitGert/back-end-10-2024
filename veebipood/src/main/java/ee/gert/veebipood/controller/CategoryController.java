package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Category;
import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.repository.CategoryRepository;
import ee.gert.veebipood.repository.ProdcutRepository;
import ee.gert.veebipood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller should be super short, everything that isn't an api enpoint should be moved to "service"

@RestController // annotation @ -> sellega votab controller  api requeste vastu
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ProdcutRepository prodcutRepository;

    @Autowired
    CategoryService categoryService;

//    @Autowired
//    private ProductRepository productRepository;

    @GetMapping("category")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping("category")
    public List<Category> addCategory(@RequestBody Category category){
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    @GetMapping("category-products/{categoryId}")
    public List<Product> getCategoryProducts(@PathVariable Long categoryId){
        return prodcutRepository.findByCategory_Id(categoryId);
    }


    
    @GetMapping("protein-category-products/{categoryId}")
    public int getProteinCategoryProducts(@PathVariable Long categoryId){
        List<Product> products = prodcutRepository.findByCategory_Id(categoryId);
        return categoryService.getProductListProteinCount(products);
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
