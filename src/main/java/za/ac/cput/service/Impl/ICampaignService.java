package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Campaign;

import java.util.List;
@Service
public interface ICampaignService extends IService<Campaign, String> {
    List<Campaign> getAll();
}
