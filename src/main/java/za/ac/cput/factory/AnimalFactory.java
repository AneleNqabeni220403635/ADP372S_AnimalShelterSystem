package za.ac.cput.factory;


import za.ac.cput.domain.Animal;
import za.ac.cput.util.Helper;

public class AnimalFactory {

    public static Animal createAnimal(Long animalCode, String name, int age, String type) {
        if (Helper.isNullorZero(animalCode) || Helper.isNullorEmpty(name) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(type)) {
            return null;
        }
        Long newAnimalId = Helper.generateAnimalCode();

        return new Animal.Builder()
                .setAnimalCode(animalCode)
                .setName(name)
                .setAge(age)
                .setType(type)
                .build();
    }

}
