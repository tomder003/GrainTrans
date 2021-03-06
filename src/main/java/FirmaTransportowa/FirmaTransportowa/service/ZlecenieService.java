/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.service;

import FirmaTransportowa.FirmaTransportowa.entity.Zlecenie;
import FirmaTransportowa.FirmaTransportowa.repository.ZlecenieRepository;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class ZlecenieService {
    @Autowired
    private ZlecenieRepository zlecenieRepository;
    
    public List<Zlecenie> getZlecenieOfDay(LocalDateTime dateTime)
    {
        return zlecenieRepository.findAll().stream().filter(z->z.getDataZaladunku().getDayOfMonth()==dateTime.getDayOfMonth()).filter(z->z.getDataZaladunku().getYear()==dateTime.getYear()).filter(z->z.getDataZaladunku().getMonth().equals(dateTime.getMonth())).collect(Collectors.toList());
    }

    public void dodajZlecenie(Zlecenie zlecenie)
    {
        zlecenieRepository.save(zlecenie);
    }

    public int iloscZlecen()
    {
        return zlecenieRepository.findAll().size();
    }

    public List<Zlecenie> getZlecenia()
    {
        LocalDateTime dzisiaj=LocalDateTime.now();
        return zlecenieRepository.findAll().stream().filter(z->z.getDataZaladunku().getYear()==dzisiaj.getYear()).filter(z->z.getDataZaladunku().getMonthValue()==dzisiaj.getMonthValue()).filter(z->z.getDataZaladunku().getDayOfMonth()>=dzisiaj.getDayOfMonth()).sorted(Comparator.comparing(Zlecenie::getDataZaladunku)).collect(Collectors.toList());
    }
}
