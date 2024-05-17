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
        private String caregoverName;
        private String caregiverContact;
        private String  caregiverAddress;
        private String hometype;
        private String capacity;
        private String experienceLevel;
        private String currentStatus;
        private String notes;

        @OneToMany(cascade = CascadeType.ALL )
        protected List <FosterRecord> fosterRecords;

        protected FosterCare(){

        }
        private FosterCare(Builder builder){
            this.caregoverName = builder.caregoverName;
            this.caregiverContact = builder.caregiverContact;
            this.caregiverAddress = builder.caregiverAddress;
            this.hometype = builder.hometype;
            this.capacity = builder.capacity;
            this.experienceLevel = builder.experienceLevel;
            this.currentStatus = builder.currentStatus;
            this.notes = builder.notes;
            this.fosterRecords = builder.fosterRecords;
        }

        public String getCaregoverName() {
            return caregoverName;
        }

        public String getCaregiverContact() {
            return caregiverContact;
        }

        public String getCaregiverAddress() {
            return caregiverAddress;
        }

        public String getHometype() {
            return hometype;
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
            return Objects.equals(caregoverName, that.caregoverName) && Objects.equals(caregiverContact, that.caregiverContact) && Objects.equals(caregiverAddress, that.caregiverAddress) && Objects.equals(hometype, that.hometype) && Objects.equals(capacity, that.capacity) && Objects.equals(experienceLevel, that.experienceLevel) && Objects.equals(currentStatus, that.currentStatus) && Objects.equals(notes, that.notes) && Objects.equals(fosterRecords, that.fosterRecords);
        }

        @Override
        public int hashCode() {
            return Objects.hash(caregoverName, caregiverContact, caregiverAddress, hometype, capacity, experienceLevel, currentStatus, notes, fosterRecords);
        }

        @Override
        public String toString() {
            return "FosterCare{" +
                    "caregoverName='" + caregoverName + '\'' +
                    ", caregiverContact='" + caregiverContact + '\'' +
                    ", caregiverAddress='" + caregiverAddress + '\'' +
                    ", hometype='" + hometype + '\'' +
                    ", capacity='" + capacity + '\'' +
                    ", experienceLevel='" + experienceLevel + '\'' +
                    ", currentStatus='" + currentStatus + '\'' +
                    ", notes='" + notes + '\'' +
                    ", fosterRecords=" + fosterRecords +
                    '}';
        }
        public static class Builder{
            private String caregoverName;
            private String caregiverContact;
            private String  caregiverAddress;
            private String hometype;
            private String capacity;
            private String experienceLevel;
            private String currentStatus;
            private String notes;
            private List <FosterRecord> fosterRecords;

            public Builder setCaregoverName(String caregoverName) {
                this.caregoverName = caregoverName;
                return this;
            }

            public Builder setCapacity(String capacity) {
                this.capacity = capacity;
                return this;
            }

            public Builder setHometype(String hometype) {
                this.hometype = hometype;
                return this;
            }

            public Builder setCaregiverAddress(String caregiverAddress) {
                this.caregiverAddress = caregiverAddress;
                return this;
            }

            public Builder setCaregiverContact(String caregiverContact) {
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
                this.caregoverName = f.caregoverName;
                this.caregiverContact = f.caregiverContact;
                this.caregiverAddress = f.caregiverAddress;
                this.hometype = f.hometype;
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


