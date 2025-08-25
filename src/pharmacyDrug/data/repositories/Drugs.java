package pharmacyDrug.data.repositories;

import org.springframework.stereotype.Repository;
import pharmacyDrug.data.models.Drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class Drugs implements DrugsImpl {

    private static List<Drug> store = new ArrayList<>();
    private static int counter = 1;


    public Drug save(Drug drug) {
        if (drug.getId() == 0) {
            drug.setId(counter++);
            store.add(drug);
        } else {
            for (int i = 0; i < store.size(); i++) {
                if (store.get(i).getId() == drug.getId()) {
                    store.set(i, drug);
                    return drug;
                }
            }
        }
        return drug;
    }


    public Drug findById(int id) {
        for (var d : store) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }


    public Optional<Drug> findByName(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        for (var drug : store) {
            if (drug.getName().equalsIgnoreCase(name)) {
                return Optional.of(drug);
            }
        }
        return Optional.empty();
    }


    public List<Drug> findAll() {
        return new ArrayList<>(store);
    }

    public boolean deleteById(int id) {
        for (int i = 0; i < store.size(); i++) {
            Drug d = store.get(i);
            if (d.getId() == id) {
                store.remove(i);
                return true;
            }
        }
        return false;

    }
    @Override
    public long count() {
        return store.size();
    }

    public boolean existsById(int id) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public int clearAll() {
        int removed = store.size();
        store.clear();
        return removed;
    }
}