package pharmacyDrug.dtos;

import lombok.Data;
import pharmacyDrug.data.models.Category;
import pharmacyDrug.data.models.Type;

import java.time.LocalDate;
@Data
public class DrugRequest {
    private String name;
    private double price;
    private Type type;
    private Category category;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private int quantity;


}
