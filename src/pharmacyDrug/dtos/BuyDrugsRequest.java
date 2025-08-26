package pharmacyDrug.dtos;

import lombok.Data;
@Data
public class BuyDrugsRequest {

        private String drugName;
        private int quantity;

}
