package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Campaign {
    @Id
    private String campaignId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String objective;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Donation> donations;

    protected Campaign() {
    }

    public Campaign(CampaignBuilder campaignBuilder) {
        this.campaignId = campaignBuilder.campaignId;
        this.name = campaignBuilder.name;
        this.startDate = campaignBuilder.startDate;
        this.endDate = campaignBuilder.endDate;
        this.objective = campaignBuilder.objective;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getObjective() {
        return objective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign campaign)) return false;
        return Objects.equals(getCampaignId(), campaign.getCampaignId()) && Objects.equals(getName(), campaign.getName()) && Objects.equals(getStartDate(), campaign.getStartDate()) && Objects.equals(getEndDate(), campaign.getEndDate()) && Objects.equals(getObjective(), campaign.getObjective());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCampaignId(), getName(), getStartDate(), getEndDate(), getObjective());
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId='" + campaignId + '\'' +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", objective='" + objective + '\'' +
                '}';
    }

    public static class CampaignBuilder {
        private String campaignId;
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String objective;

        public CampaignBuilder() {
        }
        public CampaignBuilder copy(Campaign campaign) {
            this.campaignId = campaign.campaignId;
            this.name = campaign.name;
            this.startDate = campaign.startDate;
            this.endDate = campaign.endDate;
            this.objective = campaign.objective;
            return this;
        }

        public CampaignBuilder setCampaignId(String campaignId) {
            this.campaignId = campaignId;
            return this;
        }

        public CampaignBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CampaignBuilder setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public CampaignBuilder setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public CampaignBuilder setObjective(String objective) {
            this.objective = objective;
            return this;
        }

        public Campaign build() {
            return new Campaign(this);
        }
    }
}