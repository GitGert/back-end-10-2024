package ee.gert.veebipood.controller;

import ee.gert.veebipood.cache.ProductCache;
import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.exception.ValidationException;
import ee.gert.veebipood.repository.ProductRepository;
import ee.gert.veebipood.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

//@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RestController // annotation @ -> sellega votab controller  api requeste vastu
public class ProductController {


//    List<Product> products = new ArrayList<>(Arrays.asList( new Product("fanta"), new Product("coca"), new Product("7up")));
//    List<String> emptyArrList = new ArrayList<>();
//    http://localhost:8080/products


    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductCache productCache;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/public-products")
    public Page<Product> getProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @GetMapping("/product")
    public Product getProduct(@RequestParam Long id) throws ExecutionException {
        return productCache.getProduct(id);
//        return productRepository.findById(id).orElse(null); // .get() ja .orElseThrow() on samad
    }

//    http://localhost:8080/add-product?name=
//    @GetMapping("/add-product")
//    public List<Product> addProducts(@RequestParam String name){
//            productRepository.save(new Product(name));
//        return productRepository.findAll();
//    }

//    http://localhost:8080/add-product?name=coca?category=soft-drinks   <--- järjekord ei ole tähtis
//    http://localhost:8080/delete-product/Coca <- järjekord on tähtis!
    @DeleteMapping("/delete-product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        productCache.emptyCache();
        return productRepository.findAll();
    }

    //    http://localhost:8080/add-product?name=
    @PostMapping("/products")
    public List<Product> saveProduct(@RequestBody Product product) throws ValidationException {
        productService.validateProduct(product);
        if (productRepository.findById(product.getId()).isEmpty()){
            productRepository.save(product);
        }
        return productRepository.findAll();
    }

    @PutMapping("/products")
    public List<Product> editProduct(@RequestBody Product product) throws ValidationException {
        productService.validateProduct(product);
        if (productRepository.findById(product.getId()).isPresent()){
            productRepository.save(product);
            productCache.emptyCache();
        }
        return productRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/find-by-name")
    public Page<Product> getProductsByName(@RequestParam String name, Pageable pageable){
        return productRepository.findByNameContainsIgnoreCase(name, pageable);
    }



//    @GetMapping("/test")
//    public static String[] getProductsAsArray(){
//        String[] stringArr= {"coca", "fanta", "sprite"} ;
//        stringArr[1] = "test";
//        return stringArr;
//    }
////
//    public static  void main(){
//        String[] temp = getProductsAsArray();
//        for (String value: temp) {
//            System.out.println(temp);
//        }
//    }

}
