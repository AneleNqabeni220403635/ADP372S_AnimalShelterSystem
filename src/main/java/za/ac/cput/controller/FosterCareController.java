package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.service.FosterCareService;

import java.util.Set;

@RestController
@RequestMapping("/fosterCare")
public class FosterCareController {
    @Autowired
    private FosterCareService fosterCareService;

    @PostMapping("/create")
    public FosterCare create(@RequestBody FosterCare fosterCare) {
        return fosterCareService.create(fosterCare);
    }

    @PostMapping("/read/{id}")
    public FosterCare read(@PathVariable String id) {
        return fosterCareService.read(id);
    }

    @PostMapping("/update")
    public FosterCare update(@RequestBody FosterCare fosterCare) {
        return fosterCareService.update(fosterCare);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        fosterCareService.delete(id);
    }

    @GetMapping("/getAll")
    public Set<FosterCare> getAll() {
        return fosterCareService.getAll();
    }

}
