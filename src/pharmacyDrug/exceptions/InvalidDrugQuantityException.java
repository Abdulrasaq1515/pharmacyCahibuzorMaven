package pharmacyDrug.exceptions;

public class InvalidDrugQuantityException extends RuntimeException {
  public InvalidDrugQuantityException(String message) {
    super(message);
  }
}
