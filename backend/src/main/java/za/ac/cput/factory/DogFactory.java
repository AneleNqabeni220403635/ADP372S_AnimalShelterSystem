package za.ac.cput.factory;

import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.util.HashSet;
import java.util.Set;

public class DogFactory {

    public static Dog buildDog(long dogId, String name, String size, int age, String gender, String breed, int cageNumber) {
        if (Helper.isNullorZero(dogId) || Helper.isNullorEmpty(name) || Helper.isNullorEmpty(size) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(gender) || Helper.isNullorEmpty(breed) || Helper.isLessThanOrEqualToZero(cageNumber)) {
            return null;
        }
        Dog dog = new Dog.Builder()
                .setDogId(dogId)
                .setName(name)
                .setSize(size)
                .setAge(age)
                .setGender(gender)
                .setBreed(breed)
                .setCageNumber(cageNumber)
                .build();

        return dog;
    }

    public static Dog buildDog(String name, String size, int age, String gender, String breed, int cageNumber) {
        if (Helper.isNullorEmpty(name) || Helper.isNullorEmpty(size) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(gender) || Helper.isNullorEmpty(breed) || Helper.isLessThanOrEqualToZero(cageNumber)) {
            return null;
        }

        long dogId = Helper.generateDogId();

        Dog dog = new Dog.Builder()
                .setDogId(dogId)
                .setName(name)
                .setSize(size)
                .setAge(age)
                .setGender(gender)
                .setBreed(breed)
                .setCageNumber(cageNumber)
                .build();
        return dog;
    }

}

