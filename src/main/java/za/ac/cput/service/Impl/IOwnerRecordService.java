package za.ac.cput.service.Impl;

import za.ac.cput.domain.OwnerRecord;

import java.util.Set;

public interface IOwnerRecordService extends IService<OwnerRecord,Long> {
    Set<OwnerRecord> getall();
}
