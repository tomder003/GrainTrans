/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.service;

import FirmaTransportowa.FirmaTransportowa.entity.Kierowcy;
import FirmaTransportowa.FirmaTransportowa.entity.Uzytkownik;
import FirmaTransportowa.FirmaTransportowa.repository.PracownikRepository;
import java.util.List;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class PracownikService {
    @Autowired
    private PracownikRepository pracownikRepository;
    
    public List<Kierowcy> findAll()
    {
        return pracownikRepository.findAll();
    }
    
    public void dodajPracownika(String imie, String nazwisko)
    {
        Kierowcy pracownik=new Kierowcy();
        pracownik.setImie(imie);
        pracownik.setNazwisko(nazwisko);
        pracownikRepository.save(pracownik);
    }
}
