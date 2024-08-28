package za.ac.cput.service.Impl;


import org.springframework.stereotype.Service;
import za.ac.cput.domain.Volunteer;

import java.util.Set;

@Service
public interface IVolunteerService extends IService<Volunteer,Long> {
    Set<Volunteer> getall();
}

