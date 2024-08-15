package za.ac.cput.service.Impl;


import za.ac.cput.domain.Staff;

import java.util.Set;

public interface IStaffService extends IService<Staff, Long>{
   Set<Staff> getall();
}
