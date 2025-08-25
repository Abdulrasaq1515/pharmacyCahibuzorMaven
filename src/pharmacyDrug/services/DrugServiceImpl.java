package pharmacyDrug.services;

import pharmacyDrug.dtos.AvailableDrugResponse;
import pharmacyDrug.dtos.BuyDrugsRequest;
import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.dtos.DrugResponse;

import java.util.List;
import java.util.Optional;

public interface DrugServiceImpl {

    DrugResponse addDrug(DrugRequest request);

    void buyDrug(BuyDrugsRequest buyDrugsRequest);

    Optional<List<AvailableDrugResponse>> getAvailableDrugs();
}