package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.PetOwner;

import java.util.Set;

@Service
public interface  IPetOwnerService extends IService<PetOwner,Long> {
    Set<PetOwner> getall();
}

