package FirmaTransportowa.FirmaTransportowa.service;

import FirmaTransportowa.FirmaTransportowa.entity.Trasa;
import FirmaTransportowa.FirmaTransportowa.repository.TrasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrasaService {
    @Autowired
    private TrasaRepository trasaRepository;

    public void addTrasa(Trasa trasa)
    {
        trasaRepository.save(trasa);
    }

    public List<Trasa> findAll()
    {
        return trasaRepository.findAll();
    }

    public Trasa getById(int id)
    {
        return trasaRepository.getOne(id);
    }
}
