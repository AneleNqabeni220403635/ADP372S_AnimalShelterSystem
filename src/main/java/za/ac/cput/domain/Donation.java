package za.ac.cput.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Donation {
    @Id
    private String donationId;
    private float amount;
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Campaign campaign;

    protected Donation() {
    }

    public Donation(DonationBuilder donationBuilder) {
        this.donationId = donationBuilder.donationId;
        this.amount = donationBuilder.amount;
        this.date = LocalDateTime.now(); // Use the current date and time
        this.campaign = donationBuilder.campaign;
    }

    public String getDonationId() {
        return donationId;
    }

    public float getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation donation)) return false;
        return Float.compare(getAmount(), donation.getAmount()) == 0 && Objects.equals(getDonationId(), donation.getDonationId()) && Objects.equals(getDate(), donation.getDate()) && Objects.equals(getCampaign(), donation.getCampaign());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDonationId(), getAmount(), getDate(), getCampaign());
    }

    @Override
    public String toString() {
        return "Donation{" +
                "donationId='" + donationId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", campaign=" + campaign +
                '}';
    }

    public static class DonationBuilder {
        private String donationId;
        private float amount;
        private LocalDateTime date;
        private Campaign campaign;

        public DonationBuilder() {
        }

        public DonationBuilder copy(Donation donation) {
            this.donationId = donation.donationId;
            this.amount = donation.amount;
            this.date = donation.date;
            this.campaign = donation.campaign;
            return this;
        }

        public DonationBuilder setDonationId(String donationId) {
            this.donationId = donationId;
            return this;
        }

        public DonationBuilder setAmount(float amount) {
            this.amount = amount;
            return this;
        }

        public DonationBuilder setCampaign(Campaign campaign) {
            this.campaign = campaign;
            return this;
        }

        public DonationBuilder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Donation build() {
            return new Donation(this);
        }
    }
}