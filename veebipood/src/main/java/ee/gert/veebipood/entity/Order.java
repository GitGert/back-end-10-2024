package ee.gert.veebipood.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// in PostgreSQL you cannot use User and Order names.


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name="seq",initialValue = 123000, allocationSize = 1)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @ManyToMany
//    @Column(name = "products") // redundant
//    @JoinColumn(name="product_id") //redundant
    private List<Product> products;
}

// service is always connected with a specific service
// utils is a more general function.

