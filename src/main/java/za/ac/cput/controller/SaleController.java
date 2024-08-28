package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Sale;
import za.ac.cput.service.SaleService;

import java.util.Set;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/create")
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.create(sale);
    }

    @GetMapping("/read/{id}")
    public Sale readSale(@PathVariable Long id) {
        return saleService.read(id);
    }

    @PutMapping("/update")
    public Sale updateSale(@RequestBody Sale sale) {
        return saleService.update(sale);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Long id) {
        saleService.delete(id);
    }

    @GetMapping("/getall")
    public Set<Sale> getAllSales() {
        return saleService.getall();
    }
}

