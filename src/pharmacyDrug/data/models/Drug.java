package pharmacyDrug.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document(collection = "drugs")
@Data
public class Drug {
    @id
    private String id;
    private String name;
    private double price;
    private int quantity;
    private Type type;
    private Category category;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;

    public Drug() {}

    public Drug(String name, double price, int quantity, Type type, Category category, LocalDate manufactureDate, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.category = category;
        this.manufactureDate = manufactureDate;
    }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}

