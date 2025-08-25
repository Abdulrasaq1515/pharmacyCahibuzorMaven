package pharmacyDrug.services;

import org.springframework.stereotype.Service;
import pharmacyDrug.data.models.Drug;
import pharmacyDrug.data.repositories.Drugs;
import pharmacyDrug.dtos.AvailableDrugResponse;
import pharmacyDrug.dtos.BuyDrugsRequest;
import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.dtos.DrugResponse;
import pharmacyDrug.utils.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pharmacyDrug.utils.Mapper.map;
import static pharmacyDrug.utils.Mapper.mapToAvailableDrugResponse;
import static pharmacyDrug.utils.Validator.validate;

@Service
public class DrugService implements DrugServiceImpl {

    private final Drugs repository;

    public DrugService(Drugs repository) {
        this.repository = repository;
    }

@Override
    public DrugResponse addDrug(DrugRequest request) {
        validate(request);
        Drug toSave = map(request);
        Drug saved = repository.save(toSave);
        return map(saved);
    }

    @Override
    public void buyDrug(BuyDrugsRequest buyDrugsRequest) {
        Drug drug = repository.findByName(buyDrugsRequest.getDrugName())
                .orElseThrow(() -> new IllegalArgumentException("Drug not found"));

        drug.setQuantity(drug.getQuantity() - buyDrugsRequest.getQuantity());
        repository.save(drug);
    }

    @Override
    public Optional<List<AvailableDrugResponse>> getAvailableDrugs() {
        List<Drug> allDrugs = repository.findAll();
        List<AvailableDrugResponse> available = new ArrayList<>();

        for (Drug drug : allDrugs) {
            if (drug.getQuantity() > 0 && !drug.getExpiryDate().isBefore(LocalDate.now())) {
                AvailableDrugResponse response = Mapper.mapToAvailableDrugResponse(drug);
                available.add(response);
            }
        }

        return Optional.of(available);
    }

}