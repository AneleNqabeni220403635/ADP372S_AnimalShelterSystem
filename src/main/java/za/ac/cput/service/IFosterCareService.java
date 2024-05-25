package za.ac.cput.service;

import za.ac.cput.domain.FosterCare;

import java.util.Set;

public interface IFosterCareService extends IService<FosterCare, String>{
    Set<FosterCare> getAll();
}
