package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.LostAndFound;

public interface ILostAndFoundRepository  extends JpaRepository<LostAndFound, Long>
{
}
