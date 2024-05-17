package za.ac.cput.factory;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.FosterCare;
public class FosterCareFactoryTest {



    public interface FosterCareRepository extends JpaRepository<FosterCare, String> {

    }

}
