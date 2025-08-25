package pharmacyDrug.data.repositories;

import pharmacyDrug.data.models.Drug;

import java.util.Optional;

public interface DrugsImpl {

    public Drug save(Drug drug);
    public Drug findById(int id);
    public Optional<Drug> findByName(String name);
    public long count();
    public boolean deleteById(int id);
    public boolean existsById(int id);
    public int clearAll();
}
