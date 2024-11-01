package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RestController // annotation @ -> sellega votab controller  api requeste vastu
public class ProductController {


//    List<Product> products = new ArrayList<>(Arrays.asList( new Product("fanta"), new Product("coca"), new Product("7up")));
//    List<String> emptyArrList = new ArrayList<>();
//    http://localhost:8080/products


    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public Product getProduct(@RequestParam Long id){
        return productRepository.findById(id).orElseThrow(); // .get() ja .orElseThrow() on samad
    }

    @GetMapping("/all-products")
    public List<Product> getAllProducts(){
        log.info(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return productRepository.findAll();
    }

    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

//    http://localhost:8080/add-product?name=
    @GetMapping("/add-product")
    public List<Product> addProducts(@RequestParam String name){
            productRepository.save(new Product(name));
        return productRepository.findAll();
    }

//    http://localhost:8080/add-product?name=coca?category=soft-drinks   <--- järjekord ei ole tähtis
//    http://localhost:8080/delete-product/Coca <- järjekord on tähtis!
    @GetMapping("/delete-product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    //    http://localhost:8080/add-product?name=
    @PostMapping("/product")
    public List<Product> saveProduct(@RequestBody Product product){

        productRepository.save(product);
        return productRepository.findAll();
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
