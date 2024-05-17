package za.ac.cput.factory;

import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class MedicalRecordFactory {

    public static MedicalRecord createMedicalRecord(Long animal, LocalDate vaccinationDate,
                                                    String medication, String behaviour,
                                                    LocalDate nextCheckup) {
        if(!Helper.IsValidAnimal(animal) || !Helper.IsValidLocalDateVaccinationDate(vaccinationDate) || !Helper.IsValidMedication(medication) || !Helper.IsValidBehaviour(behaviour) || !Helper.IsValidLocalDateNextCheckUp(nextCheckup))
            return null;

        return new MedicalRecord.Builder()
                .setVaccinationDate(vaccinationDate)
                .setMedication(medication)
                .setBehaviour(behaviour)
                .setNextCheckup(nextCheckup)
                .build();
    }
}
