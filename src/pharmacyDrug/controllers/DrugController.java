package pharmacyDrug.controllers;

import org.springframework.web.bind.annotation.*;
import pharmacyDrug.dtos.AvailableDrugResponse;
import pharmacyDrug.dtos.BuyDrugsRequest;
import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.dtos.DrugResponse;
import pharmacyDrug.services.DrugService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drugs")

public class DrugController {

    private final DrugService service;

    public DrugController(DrugService service) {  // Spring injects the service
        this.service = service;
    }
    
@PostMapping("/new")
    public String recordNewDrug(@RequestBody DrugRequest request) {
        service.addDrug(request);
        return "Drug added";
    }

@PostMapping("/buy")
    public String buyDrug(@RequestBody BuyDrugsRequest buyDrugsRequest) {
        service.buyDrug(buyDrugsRequest);
        return "Purchased";
    }

@GetMapping("/available")
    public Optional<List<AvailableDrugResponse>> getAvailableDrugs() {
        return service.getAvailableDrugs();
    }
}

