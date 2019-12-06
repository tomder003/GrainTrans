/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.api;

import FirmaTransportowa.FirmaTransportowa.entity.Kierowcy;
import FirmaTransportowa.FirmaTransportowa.service.PracownikService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tomek
 */
@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private PracownikService pracownikService;
    
    @RequestMapping("/pracownicy")
    public List<Kierowcy> pracownicy()
    {
        return pracownikService.findAll();
    }
    
    @PostMapping("/dodajPracownika/{imie}/{nazwisko}")
    public void dodajPracownika(@PathVariable("imie") String imie, @PathVariable("nazwisko") String nazwisko)
    {
        pracownikService.dodajPracownika(imie, nazwisko);
    }
    
}
