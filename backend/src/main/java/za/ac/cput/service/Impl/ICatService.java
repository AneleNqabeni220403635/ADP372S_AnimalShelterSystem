package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cat;

import java.util.Set;
@Service
public interface ICatService extends IService<Cat,Long> {
    Set<Cat> getall();
}
