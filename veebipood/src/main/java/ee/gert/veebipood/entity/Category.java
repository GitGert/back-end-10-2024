package ee.gert.veebipood.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany
//    List<Product> products;


}
//    strategy = GenerationType.IDENTITY 1,2,6,8,9
//    strategy = GenerationType.UUID asasd1-313-21-adasd-123
//    strategy = GenerationType.SEQUENCE configurable. for example start from 10000 move increment by 10

//      dont use AUTO, gives weird output sometimes.
//    strategy = GenerationType.IDENTITY 1,2,6,8,9
