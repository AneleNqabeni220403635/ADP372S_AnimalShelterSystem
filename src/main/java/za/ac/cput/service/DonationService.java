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
    DonationService(DonationRepository repo) {
        this.repo = repo;
    }


    @Override
    public Donation create(Donation donation) {
        return repo.save(donation);
    }

    @Override
    public Donation read(String donationId) {
        return repo.findById(donationId).orElse(null);
    }

    @Override
    public Donation update(Donation donation) {
        return repo.save(donation);
    }

    @Override
    public void delete(String donationId) {
        repo.deleteById(donationId);

    }

    @Override
    public List<Donation> getAll() {
        return repo.findAll();
    }
}

