package za.ac.cput.factory;

import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class MedicalRecordFactory {

    public static MedicalRecord buildMedicalRecord(Animal animal, LocalDate vaccinationDate, String medication, String behaviour, LocalDate nextCheckup) {
        if (animal == null || !Helper.isValidLocalDateVaccinationDate(vaccinationDate) || !Helper.isValidMedication(medication) || !Helper.isValidBehaviour(behaviour) || !Helper.isValidLocalDateNextCheckUp(nextCheckup)) {
            return null;
        }

        return new MedicalRecord.Builder()
                .setAnimal(animal)
                .setVaccinationDate(vaccinationDate)
                .setMedication(medication)
                .setBehaviour(behaviour)
                .setNextCheckup(nextCheckup)
                .build();
    }

    public static MedicalRecord buildDefaultMedicalRecord(Animal animal) {
        LocalDate defaultVaccinationDate = LocalDate.now().plusMonths(1);
        String defaultMedication = "Default Medication";
        String defaultBehaviour = "Normal";
        LocalDate defaultNextCheckup = LocalDate.now().plusMonths(6);

        return buildMedicalRecord(animal, defaultVaccinationDate, defaultMedication, defaultBehaviour, defaultNextCheckup);
    }
}
