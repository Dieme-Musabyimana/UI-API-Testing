package api.POJOs.requestPOJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variant {
    private String size;
    private String color;
    private String colorHex;
    private String sku;
    private Integer stock;
    private Double price;
}