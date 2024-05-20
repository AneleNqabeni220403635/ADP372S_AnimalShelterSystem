package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.repository.DonationRepository;

import java.util.List;
import java.util.Set;

@Service
public class DonationService implements IDonationService {


    private DonationRepository repo;
    @Autowired
    DonationService(DonationRepository repo){
        this.repo = repo;
    }
    @Override
    public Donation create(Donation donation) {
        return repo.save(donation);
    }

//    @Override
//    public Donation save(Donation donation) {
//        return this.repo.save(donation);
//    }

    @Override
    public Donation read(String donationId) {
        return this.repo.findById(donationId).orElse(null);
    }

    @Override
    public boolean delete(String donationId) {
        if(this.repo.existsById(donationId)){
            this.repo.deleteById(donationId);
            return true;
        }
        return false;

    }

    @Override
    public List<Donation> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteByCampaign(Campaign savedCampaign) {

    }

    public Donation update(Donation donation) {
        if (repo.existsById(donation.getDonationId())) {
            return repo.save(donation);
        }
        return null;
    }

    @Override
    public Set<Donation> findAll() {
        return null;
    }


}
