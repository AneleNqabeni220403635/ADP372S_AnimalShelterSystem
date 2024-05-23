package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.service.AnimalService;
import za.ac.cput.service.MedicalRecordService;

import java.util.Set;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public MedicalRecord create(@RequestBody MedicalRecord medicalRecord) {

        return medicalRecordService.create(medicalRecord);
    }

    @PostMapping("/read/{animal}")
    public MedicalRecord read(@PathVariable Long animal){

        return medicalRecordService.read(animal);
    }

    @PostMapping("/update")
    public MedicalRecord update(@RequestBody MedicalRecord medicalRecord){

        return medicalRecordService.update(medicalRecord);
    }

    @PostMapping("/delete/{animal}")
    public void delete(@PathVariable Long animal){
        medicalRecordService.delete(animal);
    }

    @GetMapping("/getall")
    public Set<MedicalRecord> getall(){

        return medicalRecordService.getall();
    }

}
