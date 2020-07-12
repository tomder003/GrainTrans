package FirmaTransportowa.FirmaTransportowa.service;

import FirmaTransportowa.FirmaTransportowa.entity.Pojazdy;
import FirmaTransportowa.FirmaTransportowa.repository.PojazdyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PojazdyService {
    @Autowired
    private PojazdyRepository pojazdyRepository;

    public List<Pojazdy> findAll()
    {
        return pojazdyRepository.findAll();
    }
}
