package ee.gert.veebipood.model.supplier;

import lombok.Data;

@Data
public class SupplierProduct {
    private int id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;
    private Rating rating;
}
