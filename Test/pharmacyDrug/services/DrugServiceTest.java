package pharmacyDrug.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacyDrug.data.models.Category;
import pharmacyDrug.data.models.Drug;
import pharmacyDrug.data.models.Type;
import pharmacyDrug.data.repositories.Drugs;
import pharmacyDrug.dtos.BuyDrugsRequest;
import pharmacyDrug.dtos.DrugRequest;
import pharmacyDrug.exceptions.InvalidDrugQuantityException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrugServiceTest {
    private DrugService service;
    private Drugs repo;

    @BeforeEach
    public void setUp() {

        repo = new Drugs();
        service = new DrugService();
        repo.clearAll();
    }

    @Test
    public void testAddDrugs() {
        assertEquals(0L, repo.count(), "Initial count 0");

    }

    @Test
    public void testAddDrugsThatCountOne() {
        DrugRequest first = new DrugRequest();
        first.setName("PARACETAMOL");
        first.setPrice(10.0);
        first.setType(Type.TABLET);
        first.setCategory(Category.ANALGESIC);
        first.setManufactureDate(LocalDate.now().minusYears(1));
        first.setExpiryDate(LocalDate.now().plusYears(3));
        first.setQuantity(5);

        service.addDrug(first);

        assertEquals(1L, repo.count(), "Count 1 ");
    }

    @Test
    public void testAddDrugsThatCountThree() {
        DrugRequest first = new DrugRequest();
        first.setName("PARACETAMOL");
        first.setPrice(10.0);
        first.setType(Type.TABLET);
        first.setCategory(Category.ANALGESIC);
        first.setManufactureDate(LocalDate.now().minusYears(1));
        first.setExpiryDate(LocalDate.now().plusYears(3));
        first.setQuantity(5);
        service.addDrug(first);
        assertEquals(1L, repo.count(), "Count 1 ");

        DrugRequest second = new DrugRequest();
        second.setName("DICLOFENAC");
        second.setPrice(20.0);
        second.setType(Type.CAPSULE);
        second.setCategory(Category.ANTIBIOTIC);
        second.setManufactureDate(LocalDate.now().minusYears(2));
        second.setExpiryDate(LocalDate.now().plusYears(3));
        second.setQuantity(10);
        service.addDrug(second);
        assertEquals(2L, repo.count(), "Count 2 ");

        DrugRequest third = new DrugRequest();
        third.setName("VITAMIN C");
        third.setPrice(30.0);
        third.setType(Type.SYRUP);
        third.setCategory(Category.VITAMIN);
        third.setManufactureDate(LocalDate.now().minusYears(2));
        third.setExpiryDate(LocalDate.now().plusYears(3));
        third.setQuantity(15);
        service.addDrug(third);
        assertEquals(3L, repo.count(), "Count 3");
        assertEquals(3, repo.findAll().size(), "All size should match count");
    }


    @Test
    void addDrugWithZeroQuantityThrowException() {
        DrugRequest request = new DrugRequest();
        request.setName("Panadol");
        request.setType(Type.CAPSULE);
        request.setCategory(Category.ANALGESIC);
        request.setQuantity(0);
        request.setManufactureDate(LocalDate.now());
        request.setExpiryDate(request.getManufactureDate().plusMonths(5));
        assertThrows(InvalidDrugQuantityException.class, () -> service.addDrug(request));
        assertEquals(0, repo.count());
    }


    @Test
    void buyDrugsQuantityReduces() {
        DrugRequest panadolRequest = new DrugRequest();
        panadolRequest.setName("Panadol");
        panadolRequest.setType(Type.CAPSULE);
        panadolRequest.setCategory(Category.ANALGESIC);
        panadolRequest.setQuantity(15);
        panadolRequest.setManufactureDate(LocalDate.now());
        panadolRequest.setExpiryDate(LocalDate.now().plusMonths(5));
        service.addDrug(panadolRequest);

        DrugRequest phensicRequest = new DrugRequest();
        phensicRequest.setName("Phensic");
        phensicRequest.setType(Type.CAPSULE);
        phensicRequest.setCategory(Category.ANALGESIC);
        phensicRequest.setQuantity(12);
        phensicRequest.setManufactureDate(LocalDate.now().minusMonths(6));
        phensicRequest.setExpiryDate(LocalDate.now().minusDays(1));
        service.addDrug(phensicRequest);

        assertEquals(1, service.getAvailableDrugs().get().size());
        assertEquals("Panadol", service.getAvailableDrugs().get().get(0).getName());

        BuyDrugsRequest buyPanadol = new BuyDrugsRequest();
        buyPanadol.setDrugName("Panadol");
        buyPanadol.setQuantity(5);
        service.buyDrug(buyPanadol);

        assertEquals(1, service.getAvailableDrugs().get().size());
        assertEquals(10, service.getAvailableDrugs().get().get(0).getQuantity());
    }

    @Test
    public void availableDrugsCanBeFoundTest() {
        DrugRequest panadol = new DrugRequest();
        panadol.setName("Panadol");
        panadol.setType(Type.CAPSULE);
        panadol.setCategory(Category.ANALGESIC);
        panadol.setQuantity(15);
        panadol.setManufactureDate(LocalDate.now());
        panadol.setExpiryDate(panadol.getManufactureDate().plusMonths(5));
        service.addDrug(panadol);
        DrugRequest phensic = new DrugRequest();
        phensic.setName("Phensic");
        phensic.setType(Type.CAPSULE);
        phensic.setCategory(Category.ANALGESIC);
        phensic.setQuantity(12);
        phensic.setManufactureDate(LocalDate.now());
        phensic.setExpiryDate(phensic.getManufactureDate().plusMonths(5));
        service.addDrug(phensic);
        assertEquals(2, service.getAvailableDrugs().get().size());
        assertEquals("Panadol", service.getAvailableDrugs().get().get(0).getName());


    }
}