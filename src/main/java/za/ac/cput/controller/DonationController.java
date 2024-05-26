package za.ac.cput.controller;


/* CampaignController.java
Controller
Author: Asanda Mbangata (222927259)
Date: 25 May 2024
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Donation;
import za.ac.cput.service.DonationService;

import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;
    @PostMapping("/create")
    public Donation create(@RequestBody Donation donation){
        return donationService.create(donation);
    }
    @PostMapping("/read/{donationId}")
    public Donation read(@PathVariable String donationId) {
        return donationService.read(donationId);
    }
    @PostMapping("/update")
    public Donation update(@RequestBody Donation donation) {
        return donationService.update(donation);
    }
    @PostMapping("/delete/{donationId}")
    public void delete(@PathVariable String donationId) {
        donationService.delete(donationId);
    }
    @PostMapping("getAll")
    public List<Donation> getAll() {
        return donationService.getAll();
    }
}
