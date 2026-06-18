package api.POJOs.requestPOJO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Double comparePrice;
    private String categoryId;
    private List<String> tags;
    private Boolean isFeatured;
    private Boolean isFlashSale;
    private Double flashSalePrice;
    private List<Variant> variants;
    private String productId;
    private String variantId;
    private int quantity;
}