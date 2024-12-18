package ee.gert.veebipood.cache;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ProductCache {
    // google guava spring cache

    // cahce responses in order to reduce the amount of queries made to the db.

    @Autowired
    ProductRepository productRepository;

    private final LoadingCache<Long, Product> productLoadingCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(50)
            .build(new CacheLoader<Long, Product>() {
                @Override
                public Product load(Long id) {
                    System.out.println("võtsin andmebaasist");
                    return productRepository.findById(id).orElseThrow();
                }
            });

    public Product getProduct(Long id) throws ExecutionException {
        System.out.println("võtan toodet");
        return productLoadingCache.get(id);
    }

    public void emptyCache() {
        productLoadingCache.invalidateAll();
    }
}
