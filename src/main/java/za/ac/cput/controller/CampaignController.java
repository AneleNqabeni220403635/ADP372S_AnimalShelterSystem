package za.ac.cput.controller;

/* CampaignController.java
Controller
Author: Asanda Mbangata (222927259)
Date: 25 May 2024
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Campaign;
import za.ac.cput.service.CampaignService;

import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;
    @PostMapping("/create")
    public Campaign create(@RequestBody Campaign campaign) {
        return campaignService.create(campaign);
    }
    @PostMapping("/read/{campaignId}")
    public Campaign read(@PathVariable String campaignId) {
        return campaignService.read(campaignId);
    }
    @PostMapping("/update")
    public Campaign update(@RequestBody Campaign campaign) {
        return campaignService.update(campaign);
    }
    @PostMapping("/delete/{campaignId}")

    public void delete(@PathVariable String campaignId) {
        campaignService.delete(campaignId);
    }
    @PostMapping("/getAll")
    public List<Campaign> getAll() {
        return campaignService.getAll();
    }
}
