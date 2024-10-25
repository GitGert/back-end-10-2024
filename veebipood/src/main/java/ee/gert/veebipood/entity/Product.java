package ee.gert.veebipood.entity;
// jakarta is hibernate
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // Entity is connected with JPA meaning each
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double price;
    String image;
    boolean active;
    String description;

    @ManyToOne
    Category category;

//    List<Category> categories;
//            @JoinColumn(name = "category_id")
//    Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Nutrients nutrients;

    public Product(String name) {
        this.name = name;
    }

}

// WITH @manyToOne
// dairy:
// milk : Category dairy
// cream : Category dairy


// WITH @OneToOne:
// user.name with user.email.


// @OneToOne   -> yksyhele seos
// @ManyToOne   -> yks, aga voib korrata
// @OneToMany   -> Mitu (list), aga iga sealolev toode tohib olla yhe korra.
// @ManyToMany  -> mitu (List), aga iga seal olev toode voib olla ka teisel kategoorial.










