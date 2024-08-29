package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Applicant;

import java.util.Set;

@Service
public interface IApplicantService extends IService<Applicant, Long>
{
    Set<Applicant> getall();
}
