package za.ac.cput.factory;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.domain.FosterRecord;
import za.ac.cput.util.Helper;

import java.util.List;
public class FosterCareFactory {

     public static FosterCare buildFosterCare(String caregiverName, int caregiverContact, String caregiverAddress, String homeType, String capacity, String experienceLevel, String currentStatus, String notes, List<FosterRecord> fosterRecords) {
            if (Helper.isNullorEmpty(caregiverName) || Helper.isLessThanOrEqualToZero(caregiverContact) || Helper.isNullorEmpty(caregiverAddress) || Helper.isNullorEmpty(homeType) || Helper.isNullorEmpty(capacity) || Helper.isNullorEmpty(experienceLevel) || Helper.isNullorEmpty(currentStatus) || Helper.isNullorEmpty(notes) || fosterRecords == null) {
                return null;
            }

            return new FosterCare.Builder()
                    .setCaregiverName(caregiverName)
                    .setCaregiverContact(caregiverContact)
                    .setCaregiverAddress(caregiverAddress)
                    .setHomeType(homeType)
                    .setCapacity(capacity)
                    .setExperienceLevel(experienceLevel)
                    .setCurrentStatus(currentStatus)
                    .setNotes(notes)
                    .setFosterRecords(fosterRecords)
                    .Build();
        }
    }



