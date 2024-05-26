package za.ac.cput.service;

import za.ac.cput.domain.LostAndFound;

import java.util.Set;

public interface ILostAndFoundService extends IService<LostAndFound, Long> {
    Set<LostAndFound>  findAll();
}
