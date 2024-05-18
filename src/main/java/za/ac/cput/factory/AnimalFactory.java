package za.ac.cput.factory;


import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

public class AnimalFactory {

    public static Animal buildAnimal(long animalCode, String name, int age, String type, MedicalRecord medicalRecord) {
        if (Helper.isNullorZero(animalCode) || Helper.isNullorEmpty(name) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(type) || medicalRecord ==null) {
            return null;
        }

        long generatedAnimalCode = Helper.generateAnimalCode();
        MedicalRecord defaultMedicalRecord = MedicalRecordFactory.buildDefaultMedicalRecord(animalCode);

        return new Animal.Builder()
                .setAnimalCode(animalCode)
                .setName(name)
                .setAge(age)
                .setType(type)
                .build();
    }


}
