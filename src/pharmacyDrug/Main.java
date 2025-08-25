package pharmacyDrug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pharmacyDrug.controllers.DrugController;
import pharmacyDrug.data.models.Category;
import pharmacyDrug.data.models.Type;
import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.dtos.BuyDrugsRequest;

import java.time.LocalDate;


@SpringBootApplication

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}