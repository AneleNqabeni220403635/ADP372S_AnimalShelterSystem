package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.repository.LostAndFoundRepository;
import za.ac.cput.domain.LostAndFound;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LostAndFoundService implements ILostAndFoundService {
    @Autowired
    private LostAndFoundRepository repository;
    private static ILostAndFoundService service = null;
    private LostAndFoundService() {
    }

    @Autowired
    LostAndFoundService(LostAndFoundRepository repository)
    {
        this.repository = repository;
    }

    public static ILostAndFoundService getService()
    {
        if (service ==null)
        {
            service = new LostAndFoundService();
        }
        return service;
    }

    @Override
    public LostAndFound create(LostAndFound lostAndFound)
    {
        return repository.save(lostAndFound);
    }

    @Override
    public LostAndFound read(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    @Override
    public LostAndFound update(LostAndFound lostAndFound)
    {
        if (repository.existsById(lostAndFound.getId()))
        {
            return repository.save(lostAndFound);
        }
        return null;
    }

    @Override
    public Set<LostAndFound> findAll()
    {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public boolean delete(Long id)
    {
        if (this.repository.existsById(id))
        {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
