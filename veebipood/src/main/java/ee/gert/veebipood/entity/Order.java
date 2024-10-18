package ee.gert.veebipood.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
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


//    @Column(name = "products") // redundant
//    @JoinColumn(name="product_id") //redundant
@OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> orderRows;

    @ManyToOne
//    @JoinColumn(name = "person_email")
    private Person person;

    private Date creation;

    @ColumnDefault("0")
    private double totalSum;
}

// service is always connected with a specific service
// utils is a more general function.

