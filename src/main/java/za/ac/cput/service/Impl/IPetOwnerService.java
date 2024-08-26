package za.ac.cput.service.Impl;

import za.ac.cput.domain.PetOwner;

import java.util.Set;

public interface  IPetOwnerService extends IService<PetOwner,Long> {
    Set<PetOwner> getall();
}

