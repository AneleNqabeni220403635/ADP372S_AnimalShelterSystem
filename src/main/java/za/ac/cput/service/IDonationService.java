package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;

import java.util.List;

@Service
public interface IDonationService extends IService<Donation, String>{
    List<Donation> getAll();

    void deleteByCampaign(Campaign savedCampaign);
}
