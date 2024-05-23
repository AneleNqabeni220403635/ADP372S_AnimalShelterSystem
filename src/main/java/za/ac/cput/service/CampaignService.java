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
    public Campaign read(String s) {
        return repo.findById(s).orElse(null);
    }

    @Override
    public Campaign update(Campaign campaign) {
        return repo.save(campaign);
    }

    @Override
    public List<Campaign> getAll() {
        return repo.findAll();
    }
}