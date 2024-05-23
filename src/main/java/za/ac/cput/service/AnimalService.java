package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Animal;
import za.ac.cput.repository.AnimalRepository;
import za.ac.cput.repository.MedicalRecordRepository;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnimalService implements IAnimalService {
    private AnimalRepository animalRepository;
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    AnimalService(AnimalRepository animalRepository,MedicalRecordRepository medicalRecordRepository ) {
        this.animalRepository = animalRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }
    @Override
    public Animal create(Animal animal){
        medicalRecordRepository.save(animal.getMedicalRecord());
        return animalRepository.save(animal);
    }
    @Override
    public Animal read(Long id){
        return animalRepository.findById(id).orElse(null);
    }
@Override
    public Animal update(Animal animal){
        medicalRecordRepository.save(animal.getMedicalRecord());
        return animalRepository.save(animal);
    }

   @Override
    public void delete(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public Set<Animal> getall() {
        return animalRepository.findAll().stream().collect(Collectors.toSet());
    }

}
