package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Nutrients;
import ee.gert.veebipood.repository.NutrientsRepository;
import ee.gert.veebipood.repository.ProdcutRepository;
import ee.gert.veebipood.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // annotation @ -> sellega votab controller  api requeste vastu
public class ProductController {


//    List<Product> products = new ArrayList<>(Arrays.asList( new Product("fanta"), new Product("coca"), new Product("7up")));
//    List<String> emptyArrList = new ArrayList<>();
//    http://localhost:8080/products


    @Autowired
    ProdcutRepository prodcutRepository;
    @Autowired
    private NutrientsRepository nutrientsRepository;


    @GetMapping("/products")
    public List<Product> getProducts(){
        return prodcutRepository.findAll();
    }

//    http://localhost:8080/add-product?name=
    @GetMapping("/add-product")
    public List<Product> addProducts(@RequestParam String name){
            prodcutRepository.save(new Product(name));
        return prodcutRepository.findAll();
    }

//    http://localhost:8080/add-product?name=coca?category=soft-drinks   <--- järjekord ei ole tähtis
//    http://localhost:8080/delete-product/Coca <- järjekord on tähtis!
    @GetMapping("/delete-product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        prodcutRepository.deleteById(id);
        return prodcutRepository.findAll();
    }

    //    http://localhost:8080/add-product?name=
    @PostMapping("/product")
    public List<Product> saveProduct(@RequestBody Product product){
        Nutrients nutrients = nutrientsRepository.save(product.getNutrients());
        product.setNutrients(nutrients);
        prodcutRepository.save(product);
        return prodcutRepository.findAll();
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
