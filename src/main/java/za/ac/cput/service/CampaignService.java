package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.repository.CampaignRepository;

import java.util.List;
import java.util.Set;

@Service
public class CampaignService implements ICampaignService {

    private CampaignRepository repo;

    @Autowired
    CampaignService(CampaignRepository repo) {
        this.repo = repo;
    }

    @Override
    public Campaign create(Campaign campaign) {
        return repo.save(campaign);
    }

    @Override
    public Campaign read(String campaignId) {
        return repo.findById(campaignId).orElse(null);
    }

    @Override
    public boolean delete(String campaignId) {
        if (repo.existsById(campaignId)) {
            repo.deleteById(campaignId);
            return true;
        }
        return false;
    }

    @Override
    public List<Campaign> getAll() {
        return repo.findAll();
    }

    @Override
    public Campaign update(Campaign campaign){
        if (repo.existsById(campaign.getCampaignId())) {
            return repo.save(campaign);
        }
        return null;
    }

    @Override
    public Set<Campaign> findAll() {
        return null;
    }

    @Override
    public void deleteByDonation(Donation savedDonation) {
        // Implement the deleteByDonation functionality
    }
}