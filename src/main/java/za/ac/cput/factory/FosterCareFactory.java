package za.ac.cput.factory;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.domain.FosterRecord;

import java.util.List;
public class FosterCareFactory {

        public static FosterCare createFosterCare(String caregiverName,
                                                  String caregiverContact,
                                                  String caregiverAddress,
                                                  String homeType,
                                                  String capacity,
                                                  String experienceLevel,
                                                  String currentStatus,
                                                  String notes,
                                                  List<FosterRecord> fosterRecords) {

            if (caregiverName == null || caregiverName.isEmpty()) {
                throw new IllegalArgumentException("Caregiver name cannot be null or empty");
            }

            if (caregiverContact == null || caregiverContact.isEmpty()) {
                throw new IllegalArgumentException("Caregiver contact cannot be null or empty");
            }

            if (caregiverAddress == null || caregiverAddress.isEmpty()) {
                throw new IllegalArgumentException("Caregiver address cannot be null or empty");
            }

            if (homeType == null || homeType.isEmpty()) {
                throw new IllegalArgumentException("Home type cannot be null or empty");
            }

            if (capacity == null || capacity.isEmpty()) {
                throw new IllegalArgumentException("Capacity cannot be null or empty");
            }

            if (experienceLevel == null || experienceLevel.isEmpty()) {
                throw new IllegalArgumentException("Experience level cannot be null or empty");
            }

            if (currentStatus == null || currentStatus.isEmpty()) {
                throw new IllegalArgumentException("Current status cannot be null or empty");
            }

            FosterCare fosterCare = new FosterCare.Builder()
                    .setCaregoverName(caregiverName)
                    .setCaregiverContact(caregiverContact)
                    .setCaregiverAddress(caregiverAddress)
                    .setHometype(homeType)
                    .setCapacity(capacity)
                    .setExperienceLevel(experienceLevel)
                    .setCurrentStatus(currentStatus)
                    .setNotes(notes)
                    .setFosterRecords(fosterRecords)
                    .Build();

            return fosterCare;
        }
    }


