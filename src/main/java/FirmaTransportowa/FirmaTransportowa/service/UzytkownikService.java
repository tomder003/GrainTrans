/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.service;

import FirmaTransportowa.FirmaTransportowa.entity.Kierowcy;
import FirmaTransportowa.FirmaTransportowa.entity.Ladunek;
import FirmaTransportowa.FirmaTransportowa.entity.Pojazdy;
import FirmaTransportowa.FirmaTransportowa.entity.Trasa;
import FirmaTransportowa.FirmaTransportowa.entity.Uzytkownik;
import FirmaTransportowa.FirmaTransportowa.entity.Zlecenie;
import FirmaTransportowa.FirmaTransportowa.enums.Rola;
import FirmaTransportowa.FirmaTransportowa.repository.KierowcyRepository;
import FirmaTransportowa.FirmaTransportowa.repository.LadunekRepository;
import FirmaTransportowa.FirmaTransportowa.repository.PojazdyRepository;
import FirmaTransportowa.FirmaTransportowa.repository.TrasaRepository;
import FirmaTransportowa.FirmaTransportowa.repository.UzytkownikRepository;
import FirmaTransportowa.FirmaTransportowa.repository.ZlecenieRepository;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class UzytkownikService {

    @Autowired
    private UzytkownikRepository uzytkownikRepository;
    @Autowired
    private KierowcyRepository kierowcyRepository;
    @Autowired
    private LadunekRepository ladunekRepository;
    @Autowired
    private PojazdyRepository pojazdyRepository;
    @Autowired
    private ZlecenieRepository zlecenieRepository;
    @Autowired
    private TrasaRepository trasaRepository;

    @PostConstruct
    private void addAdmin() {
        if (uzytkownikRepository.findAll().stream().filter(u -> u.getRola().equals("ADMIN")).count() == 0) {
            Uzytkownik uzytkownik = new Uzytkownik();
            uzytkownik.setLogin("admin");
            uzytkownik.setHaslo("admin");
            uzytkownik.setRola(Rola.ADMIN.toString());
            uzytkownikRepository.save(uzytkownik);
            Kierowcy kierowca = new Kierowcy();
            //kierowca.setBadaniaLekarskie(LocalDateTime.MAX);
            kierowca.setImie("Pan");
            kierowca.setNazwisko("Szef");
            kierowca.setNrTelefonu("123456789");
            kierowca.setPensja(3500);
            //kierowca.setTestyPsychologiczne(LocalDateTime.MAX);
            // kierowca.setUbezpieczenie(LocalDateTime.MAX);
            kierowca.setUzytkownik(uzytkownik);
            kierowcyRepository.save(kierowca);
            Ladunek ladunek = new Ladunek();
            ladunek.setLadunek("Zboże");
            ladunekRepository.save(ladunek);
            Pojazdy pojazd = new Pojazdy();
            pojazd.setLicznikKm(0);
            pojazd.setMarka("Scania");
            pojazd.setMarka("Typ 1");
            pojazd.setNrRejestracyjny("CGR 5555");
            pojazd.setPrzeglad(LocalDateTime.of(2020, Month.MARCH, 25, 0, 0));
            pojazd.setUbezpieczenie(LocalDateTime.of(2020, Month.MARCH, 25, 0, 0));
            pojazdyRepository.save(pojazd);
            Trasa trasa = new Trasa();
            trasa.setStart("Bydgoszcz");
            trasa.setCel("Grudziądz");
            trasa.setOdleglosc(75);
            trasa.setPlatna(false);
            trasaRepository.save(trasa);
            Zlecenie zlecenie = new Zlecenie();
            zlecenie.setKierowca(kierowca);
            zlecenie.setLadunek(ladunek);
            zlecenie.setPojazd(pojazd);
            zlecenie.setTrasa(trasa);
            zlecenie.setWartoscZlecenia(trasa);
            zlecenie.setDataRozladunku(LocalDateTime.of(2019, Month.DECEMBER, 7, 12, 0));
            zlecenie.setDataZaladunku(LocalDateTime.of(2019, Month.DECEMBER, 7, 10, 0));
            zlecenieRepository.save(zlecenie);
        }
    }

    public boolean login(String login, String password) {
        try {
            Uzytkownik uzytkownik = uzytkownikRepository.findAll().stream().filter(u -> u.getLogin().equals(login)).findFirst().get();
            if (uzytkownik.getHaslo().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public Kierowcy getKierowcaByLogin(String login) {
        return kierowcyRepository.findAll().stream().filter(k -> k.getUzytkownik().getLogin().equals(login)).findFirst().get();
    }

    public List<Uzytkownik> findAll()
    {
        return uzytkownikRepository.findAll();
    }
}
