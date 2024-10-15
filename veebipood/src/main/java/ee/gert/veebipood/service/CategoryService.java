package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Product;

import java.util.List;

public class CategoryService {

    public int getProductListProteinCount(List<Product> products) {
        int sum = 0;
        for (Product p : products){
            if (p.getNutrients() != null){
                sum+= p.getNutrients().getProtein();
            }
        }
        return sum;
    }
}
