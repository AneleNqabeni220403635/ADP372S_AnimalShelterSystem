package za.ac.cput.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Sale;
import za.ac.cput.repository.SaleRepository;
import za.ac.cput.service.Impl.ISaleService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService {
    private final SaleRepository repository;

    @Autowired
    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }


    public Sale create(Sale sale) {
        return repository.save(sale);
    }


    public Sale read(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Sale update(Sale sale) {
        return repository.save(sale);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<Sale> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }
}

