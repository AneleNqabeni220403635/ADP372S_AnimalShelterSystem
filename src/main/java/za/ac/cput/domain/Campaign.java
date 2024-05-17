package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
@Entity
public class Campaign {
    @Id
    private String campaignId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String objectives;

    protected Campaign() {
    }
    private Campaign(CampaignBuilder campaignBuilder){
        this.campaignId = campaignBuilder.campaignId;
        this.name = campaignBuilder.name;
        this.startDate = campaignBuilder.startDate;
        this.endDate = campaignBuilder.endDate;
        this.objectives = Arrays.toString(campaignBuilder.objectives);

    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String[] getObjectives() {
        return new String[]{objectives};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign campaign)) return false;
        return Objects.equals(getCampaignId(), campaign.getCampaignId()) && Objects.equals(getName(), campaign.getName()) && Objects.equals(getStartDate(), campaign.getStartDate()) && Objects.equals(getEndDate(), campaign.getEndDate()) && Objects.equals(getObjectives(), campaign.getObjectives());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCampaignId(), getName(), getStartDate(), getEndDate(), getObjectives());
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId='" + campaignId + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", objectives=" + Arrays.toString(new String[]{objectives}) +
                '}';
    }

    public static class CampaignBuilder{
        private String campaignId;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private String[] objectives;

        public CampaignBuilder() {
        }

        public CampaignBuilder setCampaignId(String campaignId) {
            this.campaignId = campaignId;
            return this;
        }

        public CampaignBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CampaignBuilder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public CampaignBuilder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public CampaignBuilder setObjectives(String[] objectives) {
            this.objectives = objectives;
            return this;
        }

        public Campaign build(){
            return new Campaign(this);
        }
    }
}
