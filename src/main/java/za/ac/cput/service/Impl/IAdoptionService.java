package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Adoption;

import java.util.Set;
@Service
public interface IAdoptionService extends IService<Adoption, Long>{

    Set<Adoption> getAll();
}
