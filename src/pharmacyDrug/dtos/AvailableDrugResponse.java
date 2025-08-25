package pharmacyDrug.dtos;

public class AvailableDrugResponse {
    private String name;
    private int quantity;
    private boolean expired;  // ✅ renamed properly

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AvailableDrugResponse{" +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
