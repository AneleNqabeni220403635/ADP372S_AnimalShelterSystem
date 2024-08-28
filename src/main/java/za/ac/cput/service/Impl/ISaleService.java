package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Sale;

import java.util.Set;
@Service
public interface ISaleService extends IService<Sale,Long> {
    Set<Sale> getall();
}
