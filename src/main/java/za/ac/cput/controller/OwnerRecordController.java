package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OwnerRecord;
import za.ac.cput.service.OwnerRecordService;

import java.util.Set;

@RestController
@RequestMapping("/ownerRecord")
public class OwnerRecordController {

    @Autowired
    private OwnerRecordService ownerRecordService;

    @PostMapping("/create")
    public OwnerRecord createOwnerRecord(@RequestBody OwnerRecord petOwner) {
        return ownerRecordService.create(petOwner);
    }

    @GetMapping("/read/{id}")
    public OwnerRecord readOwnerRecord(@PathVariable Long id) {
        return ownerRecordService.read(id);
    }

    @PutMapping("/update")
    public OwnerRecord updateOwnerRecord(@RequestBody OwnerRecord petOwner) {
        return ownerRecordService.update(petOwner);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOwnerRecord(@PathVariable Long id) {
        ownerRecordService.delete(id);
    }

    @GetMapping("/getall")
    public Set<OwnerRecord> getAllOwnerRecords() {
        return ownerRecordService.getall();
    }
}
