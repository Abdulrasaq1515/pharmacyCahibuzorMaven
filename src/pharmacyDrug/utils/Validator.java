package pharmacyDrug.utils;

import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.exceptions.InvalidDrugQuantityException;

public class Validator {
    public static void validate(DrugRequest request) {
        validateDrugName(request);
        validateDrugQuantity(request);
    }

    private static void validateDrugName(DrugRequest request) {
        if (request == null || request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Drug name cannot be empty");
        }
    }

    private static void validateDrugQuantity(DrugRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getQuantity() <= 0) {
            throw new InvalidDrugQuantityException("Invalid quantity, must be greater than 0");
        }
    }
}
