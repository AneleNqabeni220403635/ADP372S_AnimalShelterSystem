package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

    @Entity
    public class FosterRecord {
        @Id
        private String animalId;
        private String animalName;
        private String breed;
        private String age;
        private String gender;
        private String healthStatus;
        private String behaviorNotes;
        private String specialCare;
        private String dailyRoutine;
        private String incidentReport;

        protected FosterRecord(){}

        public FosterRecord (Builder builder){
            this.animalId =builder.animalId;
            this.animalName = builder.animalName;
            this.breed = builder.breed;
            this.age = builder.age;
            this.gender = builder.gender;
            this.healthStatus = builder.healthStatus;
            this.behaviorNotes =builder. behaviorNotes;
            this.specialCare = builder.specialCare;
            this.dailyRoutine =builder.dailyRoutine;
            this.incidentReport = builder.incidentReport;
        }

        public String getAnimalId() {
            return animalId;
        }

        public String getAnimalName() {
            return animalName;
        }

        public String getBreed() {
            return breed;
        }

        public String getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public String getBehaviorNotes() {
            return behaviorNotes;
        }

        public String getSpecialCare() {
            return specialCare;
        }

        public String getDailyRoutine() {
            return dailyRoutine;
        }

        public String getIncidentReport() {
            return incidentReport;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FosterRecord that = (FosterRecord) o;
            return Objects.equals(animalId, that.animalId) && Objects.equals(animalName, that.animalName) && Objects.equals(breed, that.breed) && Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(healthStatus, that.healthStatus) && Objects.equals(behaviorNotes, that.behaviorNotes) && Objects.equals(specialCare, that.specialCare) && Objects.equals(dailyRoutine, that.dailyRoutine) && Objects.equals(incidentReport, that.incidentReport);
        }

        @Override
        public int hashCode() {
            return Objects.hash(animalId, animalName, breed, age, gender, healthStatus, behaviorNotes, specialCare, dailyRoutine, incidentReport);
        }

        @Override
        public String toString() {
            return "FosterRecord{" +
                    "animalId='" + animalId + '\'' +
                    ", animalName='" + animalName + '\'' +
                    ", breed='" + breed + '\'' +
                    ", age='" + age + '\'' +
                    ", gender='" + gender + '\'' +
                    ", healthStatus='" + healthStatus + '\'' +
                    ", behaviorNotes='" + behaviorNotes + '\'' +
                    ", specialCare='" + specialCare + '\'' +
                    ", dailyRoutine='" + dailyRoutine + '\'' +
                    ", incidentReport='" + incidentReport + '\'' +
                    '}';
        }

        public static class Builder{

            private String animalId;
            private String animalName;
            private String breed;
            private String age;
            private String gender;
            private String healthStatus;
            private String behaviorNotes;
            private String specialCare;
            private String dailyRoutine;
            private String incidentReport;

            public Builder setAnimalId(String animalId) {
                this.animalId = animalId;
                return this;
            }

            public Builder setAnimalName(String animalName) {
                this.animalName = animalName;
                return this;
            }

            public Builder setBreed(String breed) {
                this.breed = breed;
                return this;
            }

            public Builder setAge(String age) {
                this.age = age;
                return this;
            }

            public Builder setGender(String gender) {
                this.gender = gender;
                return this;
            }

            public Builder setHealthStatus(String healthStatus) {
                this.healthStatus = healthStatus;
                return this;
            }

            public Builder setBehaviorNotes(String behaviorNotes) {
                this.behaviorNotes = behaviorNotes;
                return this;
            }

            public Builder setSpecialCare(String specialCare) {
                this.specialCare = specialCare;
                return this;
            }

            public Builder setDailyRoutine(String dailyRoutine) {
                this.dailyRoutine = dailyRoutine;
                return this;
            }

            public Builder setIncidentReport(String incidentReport) {
                this.incidentReport = incidentReport;
                return this;
            }
            public Builder copy(FosterRecord fr){
                this.animalId =fr.animalId;
                this.animalName = fr.animalName;
                this.breed = fr.breed;
                this.age = fr.age;
                this.gender = fr.gender;
                this.healthStatus = fr.healthStatus;
                this.behaviorNotes =fr. behaviorNotes;
                this.specialCare = fr.specialCare;
                this.dailyRoutine =fr.dailyRoutine;
                this.incidentReport = fr.incidentReport;
                return this;

            }

            public FosterRecord build(){
                return new FosterRecord(this);
            }
        }
    }


