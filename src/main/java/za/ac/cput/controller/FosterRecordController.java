package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.FosterRecord;
import za.ac.cput.service.FosterRecordService;

import java.util.Set;

@RestController
@RequestMapping("/fosterRecord")
public class FosterRecordController {
    @Autowired
    private FosterRecordService fosterRecordService;

    @PostMapping("/create")
    public FosterRecord create(@RequestBody FosterRecord fosterRecord) {
        return fosterRecordService.create(fosterRecord);
    }

    @PostMapping("/read/{id}")
    public FosterRecord read(@PathVariable String id) {
        return fosterRecordService.read(id);
    }

    @PostMapping("/update")
    public FosterRecord update(@RequestBody FosterRecord fosterRecord) {
        return fosterRecordService.update(fosterRecord);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        fosterRecordService.delete(id);
    }

    @GetMapping("/getAll")
    public Set<FosterRecord> getAll() {
        return fosterRecordService.getAll();
    }


}
