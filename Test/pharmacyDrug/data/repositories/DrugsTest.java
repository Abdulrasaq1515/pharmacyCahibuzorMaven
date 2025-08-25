package pharmacyDrug.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacyDrug.data.models.Category;
import pharmacyDrug.data.models.Drug;
import pharmacyDrug.data.models.Type;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrugsRepositoryTest {

    private Drugs repo;

    @BeforeEach
    void setUp() {
        repo = new Drugs();
        repo.clearAll();
    }

    @Test
    void testCountStartsAtZero() {
        assertEquals(0, repo.count());
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    void testAddIncrementsCount() {
        Drug d1 = new Drug("PARACETAMOL", 10.0, 5,Type.TABLET, Category.ANALGESIC,
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(3));
        repo.save(d1);
        assertEquals(1, repo.count());

        Drug d2 = new Drug("IBUPROFEN", 20.0, 10, Type.CAPSULE, Category.ANTIBIOTIC,
                LocalDate.now().minusYears(2), LocalDate.now().plusYears(3));
        repo.save(d2);
        assertEquals(2, repo.count());
    }

    @Test
    void testDeleteDoesNotChangeOtherNamesOrOrder() {
        Drug d1 = new Drug("PARACETAMOL", 10.0, 5,Type.TABLET, Category.ANALGESIC,
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(3));
        Drug d2 = new Drug("IBUPROFEN", 20.0, 10, Type.CAPSULE, Category.ANTIBIOTIC,
                LocalDate.now().minusYears(2), LocalDate.now().plusYears(3));
        Drug d3 = new Drug("VITAMIN C", 30.0, 15, Type.SYRUP, Category.VITAMIN,
                LocalDate.now().minusYears(3), LocalDate.now().plusYears(3));

        repo.save(d1);
        repo.save(d2);
        repo.save(d3);

        assertEquals(3, repo.count());

        boolean removed = repo.deleteById(d2.getId());
        assertTrue(removed);
        assertEquals(2, repo.count());

        List<Drug> all = repo.findAll();
        assertEquals(2, all.size());
        assertEquals("PARACETAMOL", all.get(0).getName());
        assertEquals("VITAMIN C", all.get(1).getName());

        assertNull(repo.findById(d2.getId()));
    }
}