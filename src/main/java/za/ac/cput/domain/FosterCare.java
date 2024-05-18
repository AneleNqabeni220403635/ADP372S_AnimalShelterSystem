package za.ac.cput.domain;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;


@Entity
public class FosterCare {

        @Id
        private String caregiverName;
        private int caregiverContact;
        private String caregiverAddress;
        private String homeType;
        private String capacity;
        private String experienceLevel;
        private String currentStatus;
        private String notes;

        @OneToMany(cascade = CascadeType.ALL )
        private List<FosterRecord> fosterRecords;


        protected FosterCare(){

        }
        private FosterCare(Builder builder){
            this.caregiverName = builder.caregiverName;
            this.caregiverContact = builder.caregiverContact;
            this.caregiverAddress = builder.caregiverAddress;
            this.homeType = builder.homeType;
            this.capacity = builder.capacity;
            this.experienceLevel = builder.experienceLevel;
            this.currentStatus = builder.currentStatus;
            this.notes = builder.notes;
            this.fosterRecords = builder.fosterRecords;
        }

        public String getCaregiverName() { return caregiverName; }

        public int getCaregiverContact() {
            return caregiverContact;
        }

        public String getCaregiverAddress() {
            return caregiverAddress;
        }

        public String getHomeType() {
            return homeType;
        }

        public String getCapacity() {
            return capacity;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public String getExperienceLevel() {
            return experienceLevel;
        }

        public List<FosterRecord> getFosterRecords() {
            return fosterRecords;
        }

        public String getNotes() {
            return notes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FosterCare that = (FosterCare) o;
            return Objects.equals(caregiverName, that.caregiverName) && Objects.equals(caregiverContact, that.caregiverContact) && Objects.equals(caregiverAddress, that.caregiverAddress) && Objects.equals(homeType, that.homeType) && Objects.equals(capacity, that.capacity) && Objects.equals(experienceLevel, that.experienceLevel) && Objects.equals(currentStatus, that.currentStatus) && Objects.equals(notes, that.notes) && Objects.equals(fosterRecords, that.fosterRecords);
        }

        @Override
        public int hashCode() {
            return Objects.hash(caregiverName, caregiverContact, caregiverAddress, homeType, capacity, experienceLevel, currentStatus, notes, fosterRecords);
        }

        @Override
        public String toString() {
            return "FosterCare{" +
                    "caregiverName='" + caregiverName + '\'' +
                    ", caregiverContact='" + caregiverContact + '\'' +
                    ", caregiverAddress='" + caregiverAddress + '\'' +
                    ", homeType'" + homeType + '\'' +
                    ", capacity='" + capacity + '\'' +
                    ", experienceLevel='" + experienceLevel + '\'' +
                    ", currentStatus='" + currentStatus + '\'' +
                    ", notes='" + notes + '\'' +
                    ", fosterRecords=" + fosterRecords +
                    '}';
        }
        public static class Builder{
            private String caregiverName;
            private int caregiverContact;
            private String  caregiverAddress;
            private String homeType;
            private String capacity;
            private String experienceLevel;
            private String currentStatus;
            private String notes;
            private List <FosterRecord> fosterRecords;

            public Builder caregiverName(String caregiverName) {
                this.caregiverName = caregiverName;
                return this;
            }

            public Builder setCapacity(String capacity) {
                this.capacity = capacity;
                return this;
            }

            public Builder setHomeType(String homeType) {
                this.homeType = homeType;
                return this;
            }

            public Builder setCaregiverAddress(String caregiverAddress) {
                this.caregiverAddress = caregiverAddress;
                return this;
            }

            public Builder setCaregiverContact(int caregiverContact) {
                this.caregiverContact = caregiverContact;
                return this;
            }

            public Builder setCurrentStatus(String currentStatus) {
                this.currentStatus = currentStatus;
                return this;
            }

            public Builder setExperienceLevel(String experienceLevel) {
                this.experienceLevel = experienceLevel;
                return this;
            }

            public Builder setFosterRecords(List<FosterRecord> fosterRecords) {
                this.fosterRecords = fosterRecords;
                return this;
            }

            public Builder setNotes(String notes) {
                this.notes = notes;
                return this;

            }
            public Builder copy(FosterCare f){
                this.caregiverName = f.caregiverName;
                this.caregiverContact = f.caregiverContact;
                this.caregiverAddress = f.caregiverAddress;
                this.homeType = f.homeType;
                this.capacity = f.capacity;
                this.experienceLevel = f.experienceLevel;
                this.currentStatus = f.currentStatus;
                this.notes = f.notes;
                this.fosterRecords = f.fosterRecords;
                return this;
            }
            public FosterCare Build(){
                return new FosterCare(this);
            }

        }
    }


