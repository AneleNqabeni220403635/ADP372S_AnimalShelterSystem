package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.OwnerRecord;

import java.util.Set;

@Service
public interface IOwnerRecordService extends IService<OwnerRecord,Long> {
    Set<OwnerRecord> getall();
}
