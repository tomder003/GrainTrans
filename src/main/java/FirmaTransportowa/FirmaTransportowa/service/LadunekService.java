package FirmaTransportowa.FirmaTransportowa.service;

import FirmaTransportowa.FirmaTransportowa.entity.Ladunek;
import FirmaTransportowa.FirmaTransportowa.repository.LadunekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LadunekService {
    @Autowired
    private LadunekRepository ladunekRepository;

    public List<Ladunek> findAll()
    {
        return ladunekRepository.findAll();
    }
}
