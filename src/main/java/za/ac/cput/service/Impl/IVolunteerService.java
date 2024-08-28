package za.ac.cput.service.Impl;


import za.ac.cput.domain.Volunteer;

import java.util.Set;

public interface IVolunteerService extends IService<Volunteer,Long> {
    Set<Volunteer> getall();
}

