package za.ac.cput.service.Impl;

import za.ac.cput.domain.Applicant;

import java.util.Set;

public interface IApplicantService extends IService<Applicant, Long>
{
    Set<Applicant> getAll();
}
