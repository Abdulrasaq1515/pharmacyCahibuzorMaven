package pharmacyDrug.utils;

import pharmacyDrug.data.models.Drug;
import pharmacyDrug.dtos.AvailableDrugResponse;
import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.dtos.DrugResponse;

public class Mapper {
    public static Drug map(DrugRequest request) {
        Drug drug = new Drug();
        drug.setName(request.getName());
        drug.setPrice(request.getPrice());
        drug.setQuantity(request.getQuantity());
        drug.setType(request.getType());
        drug.setCategory(request.getCategory());
        drug.setManufactureDate(request.getManufactureDate());
        drug.setExpiryDate(request.getExpiryDate());
        return drug;
    }

    public static DrugResponse map(Drug drug) {
        DrugResponse response = new DrugResponse();
        response.setDrugId(drug.getId());
        response.setDrugName(drug.getName());
        return response;
    }


    public static AvailableDrugResponse mapToAvailableDrugResponse(Drug drug) {
        AvailableDrugResponse response = new AvailableDrugResponse();
        response.setName(drug.getName());
        response.setQuantity(drug.getQuantity());
        response.setExpired(drug.isExpired());
        return response;
    }


}
