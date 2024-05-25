package za.ac.cput.service;

import za.ac.cput.domain.FosterRecord;

import java.util.Set;

public interface IFosterRecord extends IService<FosterRecord, String>{
    Set<FosterRecord> getAll();
}
