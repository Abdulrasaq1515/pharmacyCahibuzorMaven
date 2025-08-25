package pharmacyDrug.data.models;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Drug {
    private int id;
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

