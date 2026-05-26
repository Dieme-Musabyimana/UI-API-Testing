package api.POJOs.requestPOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class ProductPOJO {

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("color")
    private String color;

    @JsonProperty("size")
    private String size;

    public ProductPOJO() {}



}