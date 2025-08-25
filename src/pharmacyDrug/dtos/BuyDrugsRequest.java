package pharmacyDrug.dtos;

public class BuyDrugsRequest {

        private String drugName;
        private int quantity;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getDrugName() {
            return drugName;
        }

        public void setDrugName(String drugName) {
            this.drugName = drugName;
        }

}
